package com.example.work3.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.work3.R
import com.example.work3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()  {

    //Объявление переменной для привязки к разметке
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_first)

        binding = ActivityMainBinding.inflate(layoutInflater)

        // Получение корневого представления разметки
        val view = binding.root
        // Установка корневого представления разметки в качестве контента активности
        setContentView(view)




    }

}