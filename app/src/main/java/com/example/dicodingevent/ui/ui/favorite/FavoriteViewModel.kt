package com.example.dicodingevent.ui.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.dicodingevent.data.database.FavoriteRepository
import com.example.dicodingevent.data.response.EventsItem
import com.example.dicodingevent.data.response.convertFavoriteEventToEventItem

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val favoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun getAllFavorite(): LiveData<List<EventsItem>> {
        return favoriteRepository.getAllFavorite().map {
            val result = mutableListOf<EventsItem>()

            it.forEach { fav ->
                result.add(convertFavoriteEventToEventItem(fav))
            }

            return@map result
        }
    }
}



