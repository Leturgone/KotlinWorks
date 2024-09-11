package com.example.work3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.work3.R

class ThirdFragmentViewModel:ViewModel() {
    val _currentImage = MutableLiveData<Int>(R.drawable.varan3)
    private var imageChanged = false

    fun toggleImage() {
        if (!imageChanged) {
            _currentImage.value = R.drawable.varan3hat
            imageChanged = true
        } else {
            _currentImage.value = R.drawable.varan3
            imageChanged = false
        }
    }
}