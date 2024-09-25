package com.example.work5.model.dog



import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogs")
data class Dog(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val image: String
)
