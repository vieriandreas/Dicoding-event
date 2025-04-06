package com.example.dicodingevent.ui.ui.active

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingevent.data.response.EventsItem
import com.example.dicodingevent.data.response.ResponseEvent
import com.example.dicodingevent.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActiveEventViewModel : ViewModel() {

    private val _event = MutableLiveData<EventStatus>()
    val event : LiveData<EventStatus> = _event

    fun getActiveEvent () {
        _event.value=EventStatus.Loading(true)
        val client = ApiConfig.getApiService().getActiveEvents()
        client.enqueue(object : Callback<ResponseEvent> {
            override fun onResponse(
                call: Call<ResponseEvent>,
                response: Response<ResponseEvent>
            ) {
                _event.value=EventStatus.Loading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _event.postValue(EventStatus.Succes(responseBody.listEvents))
                    }
                } else {
                    _event.postValue(EventStatus.Error)
                }
            }
            override fun onFailure(call: Call<ResponseEvent>, t: Throwable) {
                _event.value=EventStatus.Loading(false)
            }
        })
    }
}


sealed class EventStatus {
    data class Succes (val list : List<EventsItem>) : EventStatus()
    data class Loading (val loading : Boolean) : EventStatus()
    data object Error : EventStatus()
}