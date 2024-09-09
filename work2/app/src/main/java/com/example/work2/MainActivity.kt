package com.example.work2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.work2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()  {

    //Объявление переменной для привязки к разметке
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        // Получение корневого представления разметки
        val view = binding.root
        // Установка корневого представления разметки в качестве контента активности
        setContentView(view)
        //Ручное управление транзакциями фрагментов:
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,FirstFragment()).commit()




    }

}