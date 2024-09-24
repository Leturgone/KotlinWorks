package com.example.work5.model.comic

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.graphics.Bitmap

@Entity(tableName = "comics")
data class Comic(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val image: Bitmap
)
