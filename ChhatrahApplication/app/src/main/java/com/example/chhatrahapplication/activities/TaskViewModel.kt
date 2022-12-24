package com.example.chhatrahapplication.activities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel(){

    val roll = MutableLiveData<String>()
    var title = MutableLiveData<String>()
    var des = MutableLiveData<String>()
}