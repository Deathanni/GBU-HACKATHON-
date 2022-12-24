package com.example.chhatrahapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chhatrahapplication.R
import com.example.chhatrahapplication.SharedPrefManager
import com.example.chhatrahapplication.databinding.ActivityInfoBinding

class Info : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding
    private lateinit var taskViewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        setContentView(binding.root)

        val tkn = SharedPrefManager.getInstance(applicationContext).reqToken
        val rollno = SharedPrefManager.getInstance(applicationContext).reqRoll
            binding.studentRoll.text = String.format("Roll no :- %s ", rollno )

    }
}