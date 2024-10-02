package com.example.work7

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.work7.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bitmap: Bitmap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            val image_url = binding.editTextText.text.toString()

            if(image_url.isNotEmpty()){
                runBlocking() {
                    launch(Dispatchers.IO){
                        bitmap = downloadImageByURl(image_url)
                        Log.i("Network","Изображение скачано")
                        launch(Dispatchers.Main) {
                            binding.imageView.setImageBitmap(bitmap)
                        }
                    }
                    launch(Dispatchers.IO) {
                        saveImageToDisk(bitmap)

                    }
                }
            }

        }
    }

    private fun downloadImageByURl(imageUrl: String): Bitmap {
        return try {
            val inputStream = URL(imageUrl).openStream()
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            throw e
        }
    }
    private  fun saveImageToDisk(bitmap: Bitmap) {
        val file = File(getOutputDirectory(), "image.jpg")
        file.outputStream().use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
        }
        Log.i("Disk","Фото сохранено: ${file.absolutePath}")
        Toast.makeText(this, "Фото сохранено: ${file.absolutePath}", Toast.LENGTH_SHORT).show()
    }
    private fun getOutputDirectory(): File {
        val mediaDir = this.externalMediaDirs.firstOrNull()?.let {
            File(this.filesDir, "photos").apply { mkdirs() }
        }
        return mediaDir ?: this.filesDir
    }
}