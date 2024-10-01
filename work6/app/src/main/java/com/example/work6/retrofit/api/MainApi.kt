package com.example.work6.retrofit.api

import com.example.work6.retrofit.model.ReceptModel
import retrofit2.http.GET
import retrofit2.http.Path

interface MainApi {
    @GET("recipes/{id}")
    suspend fun getReseptById(@Path("id") id:Int): ReceptModel
}