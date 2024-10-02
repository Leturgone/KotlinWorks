package com.example.work5


import androidx.room.Room
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.work5.databinding.ActivityMainBinding
import com.example.work5.model.recept.ReceptDB
import com.example.work5.model.recept.ReceptsRepository
import com.example.work5.retrofit.api.MainApi
import com.example.work5.viewmodel.ReceptListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        binding = ActivityMainBinding.inflate(layoutInflater)

        val retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com/").addConverterFactory(GsonConverterFactory.create()).build()

        val receptsApi = retrofit.create(MainApi::class.java)


        val db = Room.databaseBuilder(
            applicationContext, ReceptDB::class.java,"recept_db").build()
        val receptsDao= db.receptsDao()
        val recyclerView = binding.reycler
        val dog_button = binding.dogButton
        val rep = ReceptsRepository(receptsDao,receptsApi)

        dog_button.setOnClickListener{
            Log.i("log","Нажалось")

            CoroutineScope(Dispatchers.IO).launch {
                rep.getReceptsFromApi()
                val images = rep.getAllDogsFromBase()
                runOnUiThread {
                    recyclerView.adapter = ReceptListAdapter(images)
                }
            }
        }

    }

}