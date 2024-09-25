package com.example.work5


import androidx.room.Room
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.work5.databinding.ActivityMainBinding
import com.example.work5.model.recept.ReceptDB
import com.example.work5.model.recept.ReceptsRepository
import com.example.work5.retrofit.api.MainApi
import com.example.work5.viewmodel.ReceptListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com/").addConverterFactory(GsonConverterFactory.create()).build()

        val receptsApi = retrofit.create(MainApi::class.java)


        val db = Room.databaseBuilder(
            applicationContext, ReceptDB::class.java,"recept_db").build()
        val receptsDao= db.receptsDao()

        val recyclerView = binding.reycler
        val adapter = ReceptListAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


        val recept_button = binding.dogButton
        progressBar = binding.progressBar

        val rep = ReceptsRepository(receptsDao,receptsApi)

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