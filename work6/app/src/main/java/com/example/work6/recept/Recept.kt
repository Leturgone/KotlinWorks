package com.example.work6.recept



import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recepts")
data class Recept(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val difficulty: String

)
