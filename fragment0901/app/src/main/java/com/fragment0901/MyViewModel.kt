package com.fragment0901

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    val selectnum = MutableLiveData<Int>(0)
    fun setLiveData(num:Int){
    selectnum.value = num

    }
}