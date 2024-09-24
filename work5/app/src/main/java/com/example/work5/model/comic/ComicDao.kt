package com.example.work5.model.comic

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface ComicDao {
    @Insert
    fun insert(comic: Comic)
    @Query("SELECT * FROM comics")
    fun getAllComic():List<Comic>
}