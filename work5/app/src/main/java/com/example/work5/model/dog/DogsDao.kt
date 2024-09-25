package com.example.work5.model.dog

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DogsDao {
    @Insert
    fun insert(dog: Dog)
    @Query("SELECT * FROM dogs")
    fun getAllDogs():MutableList<Dog>
}