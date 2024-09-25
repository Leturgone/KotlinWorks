package com.example.work5.model.recept

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ReceptsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(recept: Recept)
    @Query("SELECT * FROM recepts")
    fun getAllRecepts():List<Recept>
}