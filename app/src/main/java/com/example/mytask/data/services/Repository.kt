package com.example.mytask.data.services

import com.example.mytask.data.models.RestCountriesModel
import retrofit2.Call
import javax.inject.Inject

class Repository @Inject constructor(private val remoteApiService: ApiServices) {

    fun getRestCountriesList(): Call<List<RestCountriesModel>> {
        return remoteApiService.getChatListData()
    }
}