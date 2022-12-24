package com.example.chhatrahapplication.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.chhatrahapplication.SharedPrefManager
import com.example.chhatrahapplication.api.RestApiService
import com.example.chhatrahapplication.databinding.FragmentAddingQueryBinding
import com.example.chhatrahapplication.models.AddQuery
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddingQuery : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddingQueryBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)
        binding.submitQuery.setOnClickListener {
            saveAction()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddingQueryBinding.inflate(inflater,container, false)
        return binding.root
    }

    private fun saveAction() {
        taskViewModel.title.value = binding.readTitle.text.toString()
        taskViewModel.des.value = binding.readDes.text.toString()
        val applicationContext = requireActivity().getApplicationContext()
        val tkn = SharedPrefManager.getInstance(applicationContext).reqToken
        val rollno = SharedPrefManager.getInstance(applicationContext).reqRoll
        val profile = Profile()
        profile.addQuery(rollno,tkn,binding.readTitle.text.toString(),binding.readDes.text.toString(),"AA")
        binding.readTitle.setText("")
        binding.readDes.setText("")
        dismiss()
    }

}