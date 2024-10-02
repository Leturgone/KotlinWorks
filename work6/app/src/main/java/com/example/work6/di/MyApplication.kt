package com.example.work6.di

import android.app.Application
import androidx.room.Room
import com.example.work6.recept.ReceptDB
import com.example.work6.recept.ReceptsRepository
import com.example.work6.retrofit.api.MainApi
import com.example.work6.viewmodel.ReceptListAdapter
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {
    private val appModule = module{
        single {
            Retrofit.Builder().baseUrl("https://dummyjson.com/").addConverterFactory(
            GsonConverterFactory.create()).build().create(MainApi::class.java) }
        single { Room.databaseBuilder(applicationContext, ReceptDB::class.java,"recept_db")
            .build().receptsDao()}
    }
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}
