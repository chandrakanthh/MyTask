package com.example.mytask.data.services

import com.example.mytask.data.models.RestCountriesModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {
    @GET("all")
    fun getChatListData(): Call<List<RestCountriesModel>>
}