package com.example.work5.retrofit.api

import com.example.work5.retrofit.model.ReceptModel
import retrofit2.http.GET
import retrofit2.http.Path

interface MainApi {
    @GET("recipes/{id}")
    suspend fun getReseptById(@Path("id") id:Int): ReceptModel
}