package com.example.dicodingevent.data.retrofit

import com.example.dicodingevent.data.response.ResponseEvent
import com.example.dicodingevent.data.response.ResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("detail/{id}")
    fun getRestaurant(
        @Path("id") id: String
    ): Call<ResponseItem>

    @GET("events")
    fun getActiveEvents() : Call<ResponseEvent>
}