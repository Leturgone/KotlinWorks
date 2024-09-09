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
        var newFragnent = FirstFragment.newInstance()

        binding = ActivityMainBinding.inflate(layoutInflater)

        // Получение корневого представления разметки
        val view = binding.root
        // Установка корневого представления разметки в качестве контента активности
        setContentView(view)
        //Ручное управление транзакциями фрагментов:
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment_tag,newFragnent).addToBackStack(null).commit()



    }
}