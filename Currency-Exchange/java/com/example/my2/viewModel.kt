package com.example.my2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class viewModel : ViewModel() {
    var number = 0

    val amount : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
}