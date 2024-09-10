package com.example.work3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FirstFragmentViewModel: ViewModel() {
    private val _currentImage = MutableLiveData<Int>(R.drawable.varan1) //хранит текущее изображение. И
    // нициализируется с изображением varan1.
    val currentImage  = _currentImage
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