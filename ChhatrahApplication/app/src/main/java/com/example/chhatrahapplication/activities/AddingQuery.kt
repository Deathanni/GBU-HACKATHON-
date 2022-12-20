package com.example.chhatrahapplication.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.chhatrahapplication.databinding.FragmentAddingQueryBinding
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
        binding.readTitle.setText("")
        binding.readDes.setText("")
        dismiss()
    }


}