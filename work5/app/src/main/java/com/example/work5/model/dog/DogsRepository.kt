package com.example.work5.model.dog

import com.example.work5.retrofit.api.MainApi

class DogsRepository(private  val dogsDao: DogsDao, private val dogApi: MainApi) {

    suspend fun getDogsFromApi(){
        val response = dogApi.getDog()
        if (response.status == "success"){
            val imageUrl = response.image
            if (imageUrl != null) {
                val dog = Dog(image = imageUrl)
                insert(dog)
            }
        }
    }

    private suspend fun insert(dog: Dog){
        dogsDao.insert(dog)
    }
    suspend fun getAllDogsFromBase() : MutableList<Dog> {
        return dogsDao.getAllDogs()
    }

}