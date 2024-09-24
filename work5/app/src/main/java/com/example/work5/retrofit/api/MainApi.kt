package com.example.work5.retrofit.api

import com.example.work5.retrofit.model.ComicModel
import retrofit2.http.GET
import retrofit2.http.Path

interface MainApi {
    @GET("comic/id")
    suspend fun getComicById(@Path("id") id: Int) : ComicModel
}