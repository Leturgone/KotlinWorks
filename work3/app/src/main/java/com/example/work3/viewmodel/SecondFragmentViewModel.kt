package com.example.work3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.work3.R

class SecondFragmentViewModel: ViewModel() {
    val _currentImage = MutableLiveData<Int>(R.drawable.varan2)
    private var imageChanged = false

    fun toggleImage() {
        if (!imageChanged) {
            _currentImage.value = R.drawable.varan2hat
            imageChanged = true
        } else {
            _currentImage.value = R.drawable.varan2
            imageChanged = false
        }
    }
}