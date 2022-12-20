package com.example.chhatrahapplication.activities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel(){

    var title = MutableLiveData<String>()
    var des = MutableLiveData<String>()
}