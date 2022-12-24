package com.example.chhatrahapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.chhatrahapplication.R
import com.example.chhatrahapplication.SharedPrefManager
import com.example.chhatrahapplication.api.RestApiService
import com.example.chhatrahapplication.databinding.ActivityQueriesResolvedBinding
import com.example.chhatrahapplication.models.AddQuery
import com.example.chhatrahapplication.models.QueriesOnList

class QueriesResolved : AppCompatActivity() {

    private lateinit var taskViewModel : TaskViewModel
    private lateinit var binding : ActivityQueriesResolvedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQueriesResolvedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        val tkn = SharedPrefManager.getInstance(applicationContext).reqToken
        val rollno = SharedPrefManager.getInstance(applicationContext).reqRoll
            binding.studentRoll.text = String.format("Roll No : %s",rollno)
        viewQueries(rollno , tkn)
        binding.setting.setOnClickListener {
            SharedPrefManager.getInstance(applicationContext).clear()
            val intent = Intent(this, HomePage::class.java)
            Toast.makeText(applicationContext,"Logout Initiated", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }
        binding.phome.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
        binding.info.setOnClickListener {
            val intent = Intent(this, Info::class.java)
            startActivity(intent)



        }
    }

    fun viewQueries(rollno: String?,tkn:String?){
        val apiService = RestApiService()

        val data = QueriesOnList(
            uid = "",
            roll_no = rollno,
            token =tkn,
            complaint_text = "",
            complaint_text_title = "",
            hostel_code = "",
            query_resolved = ""
        )

        apiService.seeQuery(data) {
            if(!it?.uid.isNullOrEmpty()){
                val title = it?.complaint_text.toString()
                val des = it?.complaint_text_title.toString()
                    binding.queryTitle.text = String.format("Title : %s",title)
                    binding.queryDes.text = String.format("Description : %s",des)
                    binding.queryStatus.text = String.format("Status : %s",it?.query_resolved)

            }
        }
    }

}