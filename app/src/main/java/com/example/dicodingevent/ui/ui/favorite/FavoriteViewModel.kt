package com.example.dicodingevent.ui.ui.favorite

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import com.example.dicodingevent.data.database.FavoriteRepository
import com.example.dicodingevent.data.response.EventsItem
import com.example.dicodingevent.data.response.ResponseEvent
import com.example.dicodingevent.data.response.convertFavoriteEventToEventItem
import com.example.dicodingevent.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    private val favoriteRepository: FavoriteRepository = FavoriteRepository(application)
    private val _event = MutableLiveData<FavoriteStatus>()
    val event : LiveData<FavoriteStatus> = _event

    fun getAllFavorite() : LiveData<List<EventsItem>> {
        return favoriteRepository.getAllFavorite().map {
            val result = mutableListOf<EventsItem>()

            it.forEach { fav ->
                result.add(convertFavoriteEventToEventItem(fav))
            }

            return@map result
        }
    }
}

sealed class FavoriteStatus {
    data class Succes (val list : List<EventsItem>) : FavoriteStatus()
    data class Loading (val loading : Boolean) : FavoriteStatus()
    data object Error : FavoriteStatus()
}

