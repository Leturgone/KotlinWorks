package com.example.work5.model.dog

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Dog::class], version = 1)
abstract class DogDB: RoomDatabase() {
    abstract fun dogDao(): DogsDao
}