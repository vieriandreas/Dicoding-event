package com.example.dicodingevent.data.retrofit

import com.example.dicodingevent.data.response.ResponseEvent
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("events?active=1")
    fun getActiveEvents() : Call<ResponseEvent>

    @GET("events?active=0")
    fun getPastEvents() : Call<ResponseEvent>
}