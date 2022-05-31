package com.example.testapi.api

import com.example.testapi.UserItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("posts")
    fun getDetails(): Call<List<UserItem>>

}