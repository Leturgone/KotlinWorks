package com.example.work6.recept

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.work6.recept.Recept

@Dao
interface ReceptsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(recept: Recept)
    @Query("SELECT * FROM recepts")
    fun getAllRecepts():List<Recept>
}