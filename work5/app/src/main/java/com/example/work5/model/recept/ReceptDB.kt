package com.example.work5.model.recept

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Recept::class], version = 1)
abstract class ReceptDB: RoomDatabase() {
    abstract fun receptsDao(): ReceptsDao
}