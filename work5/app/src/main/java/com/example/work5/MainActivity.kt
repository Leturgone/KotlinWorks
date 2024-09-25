package com.example.work5


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.work5.databinding.ActivityMainBinding
import com.example.work5.model.dog.DogDB
import com.example.work5.model.dog.DogsRepository
import com.example.work5.retrofit.api.MainApi
import com.example.work5.viewmodel.DogListAdapter
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

        val retrofit = Retrofit.Builder().baseUrl("https://dog.ceo/api/").addConverterFactory(GsonConverterFactory.create()).build()

        val dogApi = retrofit.create(MainApi::class.java)


        val db = Room.databaseBuilder(
            applicationContext, DogDB::class.java,"dog db").build()
        val dogsDao= db.dogDao()
        val recyclerView = binding.reycler
        val dog_button = binding.dogButton
        val rep = DogsRepository(dogsDao,dogApi)

        dog_button.setOnClickListener{

            CoroutineScope(Dispatchers.IO).launch {
                rep.getDogsFromApi()
                val images = rep.getAllDogsFromBase()
                runOnUiThread {
                    recyclerView.adapter = DogListAdapter(images)
                }
            }
        }

    }

}