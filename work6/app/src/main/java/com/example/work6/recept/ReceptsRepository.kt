package com.example.work6.recept

import android.util.Log

import com.example.work6.retrofit.api.MainApi

class ReceptsRepository(private  val receptsDao: ReceptsDao, private val receptsApi: MainApi) {
    suspend fun getReceptsFromApi(){

        for( i in 1..10) {
            val response = receptsApi.getReseptById(i)
            Log.i("STATUS", response.toString())
            val recept = Recept(response.id, response.name, response.difficulty)
            insert(recept)
            Log.i("log","Загружено в БД")
        }
    }

    private suspend fun insert(recept: Recept){
        receptsDao.insert(recept)
    }
    suspend fun getAllDogsFromBase() : List<Recept> {
        return receptsDao.getAllRecepts()
    }

}