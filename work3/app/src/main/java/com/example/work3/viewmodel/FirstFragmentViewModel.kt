package com.example.work3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.work3.R

class FirstFragmentViewModel: ViewModel() {
    val _currentImage = MutableLiveData<Int>(R.drawable.varan1) //хранит текущее изображение. И
    private var imageChanged = false

    fun toggleImage() {
        if (!imageChanged) {
            _currentImage.value = R.drawable.varan1hat
            imageChanged = true
        } else {
            _currentImage.value = R.drawable.varan1
            imageChanged = false
        }
    }

}