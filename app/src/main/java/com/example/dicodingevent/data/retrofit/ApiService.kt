package com.example.dicodingevent.data.retrofit

import com.example.dicodingevent.data.response.ResponseEvent
import com.example.dicodingevent.data.response.ResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("events")
    fun getActiveEvents() : Call<ResponseEvent>

    @GET("events?active=0")
    fun getPastEvents() : Call<ResponseEvent>
}