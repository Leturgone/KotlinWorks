package com.example.work5.retrofit.api

import com.example.work5.retrofit.model.DogModel
import retrofit2.http.GET

interface MainApi {
    @GET("api/breeds/image/random")
    suspend fun getDog(): DogModel
}