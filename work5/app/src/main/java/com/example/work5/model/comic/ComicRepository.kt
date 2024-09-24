package com.example.work5.model.comic

import androidx.lifecycle.LiveData

class ComicRepository(private  val comicDao: ComicDao) {
    val allComics: List<Comic> = comicDao.getAllComic()

    suspend fun insert(comic: Comic){
        comicDao.insert(comic)
    }
    suspend fun getAll(){
        comicDao.getAllComic()
    }

}