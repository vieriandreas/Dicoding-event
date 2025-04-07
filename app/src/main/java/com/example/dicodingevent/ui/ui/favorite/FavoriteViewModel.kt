package com.example.dicodingevent.ui.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dicodingevent.data.database.DbModule
import com.example.dicodingevent.data.response.EventsItem
import com.example.dicodingevent.data.response.ResponseEvent
import com.example.dicodingevent.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteViewModel(private val db: DbModule): ViewModel() {

    private val _event = MutableLiveData<FavoriteStatus>()
    val event : LiveData<FavoriteStatus> = _event

    fun getFavorite () {
        _event.value=FavoriteStatus.Loading(true)
        val client = ApiConfig.getApiService().getPastEvents()
        client.enqueue(object : Callback<ResponseEvent> {
            override fun onResponse(
                call: Call<ResponseEvent>,
                response: Response<ResponseEvent>
            ) {
                _event.value=FavoriteStatus.Loading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _event.postValue(FavoriteStatus.Succes(responseBody.listEvents))
                    }
                } else {
                    _event.postValue(FavoriteStatus.Error)
                }
            }
            override fun onFailure(call: Call<ResponseEvent>, t: Throwable) {
                _event.value=FavoriteStatus.Loading(false)
            }
        })
    }
}

class Factory(private val db: DbModule) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = FavoriteViewModel(db) as T
}

sealed class FavoriteStatus {
    data class Succes (val list : List<EventsItem>) : FavoriteStatus()
    data class Loading (val loading : Boolean) : FavoriteStatus()
    data object Error : FavoriteStatus()
}

