package com.example.fragmentcolorchange

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){
    val position = MutableLiveData<Int>()
}