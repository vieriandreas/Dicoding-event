package com.example.dicodingevent.ui.ui.past

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingevent.data.response.EventsItem
import com.example.dicodingevent.data.response.ResponseEvent
import com.example.dicodingevent.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PastEventViewModel : ViewModel() {

    private val _event = MutableLiveData<PastEventStatus>()
    val event : LiveData<PastEventStatus> = _event

    fun getPastEvent () {
        _event.value=PastEventStatus.Loading(true)
        val client = ApiConfig.getApiService().getPastEvents()
        client.enqueue(object : Callback<ResponseEvent> {
            override fun onResponse(
                call: Call<ResponseEvent>,
                response: Response<ResponseEvent>
            ) {
                _event.value=PastEventStatus.Loading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _event.postValue(PastEventStatus.Succes(responseBody.listEvents))
                    }
                } else {
                    _event.postValue(PastEventStatus.Error)
                }
            }
            override fun onFailure(call: Call<ResponseEvent>, t: Throwable) {
                _event.value=PastEventStatus.Loading(false)
            }
        })
    }
}


sealed class PastEventStatus {
    data class Succes (val list : List<EventsItem>) : PastEventStatus()
    data class Loading (val loading : Boolean) : PastEventStatus()
    data object Error : PastEventStatus()
}

