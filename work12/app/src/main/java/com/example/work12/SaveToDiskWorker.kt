package com.example.work12

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.net.URL
import java.util.Date

class SaveToDiskWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        val imageUrl = inputData.getString("image_url") ?: return Result.failure()

        return try {
            val bitmap = downloadImageByURl(imageUrl)
            if (bitmap == null) {
                Log.i("DownloadImageWorker", "Ошибка при скачивании")
            }
            val path = bitmap?.let { saveImageToDisk(it) }
            Log.i("DownloadImageWorker", "Изображение сохранено по пути: $path")
            val outputData = workDataOf("path" to path)
            Result.success(outputData)
        } catch (e: Exception) {
            Log.e("DownloadImageWorker", "Ошибка загрузки изображения: ${e.message}")
            Result.failure()
        }
    }
    private fun saveImageToDisk(bitmap: Bitmap): String {
        val file = File(getOutputDirectory(), "${Date()}.jpg")
        file.outputStream().use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
        }
        Log.i("Disk", "Фото сохранено: ${file.absolutePath}")
        return file.absolutePath
    }

    private fun getOutputDirectory(): File {
        val mediaDir = applicationContext.externalMediaDirs.firstOrNull()?.let {
            File(applicationContext.filesDir, "photos").apply { mkdirs() }
        }
        return mediaDir ?: applicationContext.filesDir
    }
    fun downloadImageByURl(imageUrl: String): Bitmap? {
        return try {
            val inputStream = URL(imageUrl).openStream()
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            Log.e("Network", "Ошибка при скачивании")
            return null
        }
    }
}