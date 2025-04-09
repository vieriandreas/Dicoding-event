package com.example.dicodingevent.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingevent.data.database.FavoriteEntity
import com.example.dicodingevent.data.database.FavoriteRepository
import com.example.dicodingevent.data.response.EventsItem
import com.example.dicodingevent.data.response.convertEventItemToFavoriteEvent

class DetailViewModel (application: Application) : ViewModel() {
    private val favoriteRepository: FavoriteRepository = FavoriteRepository(application)
    fun insert(eventsItem: EventsItem) {
        favoriteRepository.insert(convertEventItemToFavoriteEvent(eventsItem))
    }

    fun isFavoriteEvent(id: Int): LiveData<FavoriteEntity> {
        return favoriteRepository.isFavoriteEvent(id)
    }

    fun delete(eventsItem: EventsItem) {
        favoriteRepository.delete(convertEventItemToFavoriteEvent(eventsItem))
    }
}

