package com.example.mytask.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytask.data.models.RestCountriesModel
import com.example.mytask.data.services.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RestCountriesViewModel @Inject constructor(
    private val repositorySDK: Repository
) : ViewModel() {
    private val _restCountriesData = MutableLiveData<List<RestCountriesModel>>()
    val restCountriesData : LiveData<List<RestCountriesModel>>
        get() = _restCountriesData

    init {
        getRestCountriesReposData()
    }

    private fun getRestCountriesReposData(dispatcher: CoroutineDispatcher = Dispatchers.IO) {
        viewModelScope.launch(dispatcher){
            val response = repositorySDK.getRestCountriesList()
            response.enqueue(object : Callback<List<RestCountriesModel>> {
                override fun onResponse(
                    call: Call<List<RestCountriesModel>>,
                    response: Response<List<RestCountriesModel>>
                ) {
                    if(response.isSuccessful) _restCountriesData.postValue(response.body())
//                    else _restCountriesData.postValue(BaseResponse.Error("Something went wrong. please try again later"))
                }

                override fun onFailure(
                    call: Call<List<RestCountriesModel>>,
                    t: Throwable
                ) {
                    Log.e("TAG", "onFailure: ${t.message}", )
                   // _restCountriesData.postValue(t.message.toString())
                }
            })

        }
    }
}