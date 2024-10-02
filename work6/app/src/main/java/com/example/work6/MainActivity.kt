package com.example.work6


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.work6.databinding.ActivityMainBinding
import com.example.work6.recept.ReceptsDao
import com.example.work6.recept.ReceptsRepository

import com.example.work6.retrofit.api.MainApi
import com.example.work6.viewmodel.ReceptListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var progressBar: ProgressBar
    private val receptApi: MainApi by inject()
    private val receptsDao : ReceptsDao by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val recyclerView = binding.reycler
        val adapter = ReceptListAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


        val recept_button = binding.dogButton
        progressBar = binding.progressBar

        val rep = ReceptsRepository(receptsDao,receptApi)

        recept_button.setOnClickListener{
            Log.i("log","Нажалось")
            progressBar.visibility = View.VISIBLE
            recyclerView.visibility = View.INVISIBLE
            CoroutineScope(Dispatchers.IO).launch {
                rep.getReceptsFromApi()
                val images = rep.getAllDogsFromBase()
                Log.i("log","Загружено из БД")
                withContext(Dispatchers.Main) {
                    adapter.updateData(images) // Передаем новые данные в адаптер
                    recyclerView.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE

                }
            }
        }

    }

}