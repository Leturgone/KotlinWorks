package com.example.work5.model.recept

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ReceptsDao {
    @Insert
    fun insert(recept: Recept)
    @Query("SELECT * FROM recepts")
    fun getAllRecepts():MutableList<Recept>
}