package com.example.work4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.work4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //loadFragment(CameraFragment())
        binding.bottomNavigationView.setOnItemSelectedListener() {
            when (it.itemId){
                R.id.camera ->{
                    loadFragment(CameraFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.list ->{
                    loadFragment(ListFragment())
                    return@setOnItemSelectedListener true
                }else ->{
                    return@setOnItemSelectedListener false
                }

            }
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_fragment,fragment)
        transaction.commit()
    }
}