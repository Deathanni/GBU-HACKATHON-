package com.example.chhatrahapplication.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.chhatrahapplication.R
import com.example.chhatrahapplication.SharedPrefManager
import com.example.chhatrahapplication.api.RestApiService
import com.example.chhatrahapplication.databinding.ActivityProfileBinding
import com.example.chhatrahapplication.models.AddQuery


class Profile : AppCompatActivity() {

    private lateinit var setting : ImageButton
    private lateinit var phome : ImageButton
    private lateinit var infop : ImageButton
    private lateinit var binding: ActivityProfileBinding
    private lateinit var taskViewModel : TaskViewModel
    private  lateinit var submit_query : Button

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setting = findViewById(R.id.setting)
        phome = findViewById(R.id.phome)
        infop = findViewById(R.id.info)
        submit_query = findViewById(R.id.add_query)
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        val rollno = SharedPrefManager.getInstance(applicationContext).reqRoll
            binding.studentRoll.text = String.format("Roll no :- %s ", rollno )
            submit_query.text = String.format("Add Query")
            binding.apply {

                submit_query.setOnClickListener {
                    if (binding.queryTitle.text == "Title :" || binding.queryDes.text == "Description :"){
                    AddingQuery().show(supportFragmentManager, "NewTaskTag")
                    }
                }
            }
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        taskViewModel.title.observe(this){
            binding.queryTitle.text = String.format("Title : %s", it )
        }
        taskViewModel.des.observe(this){

            binding.queryDes.text = String.format("Description : %s", it )
        }

        taskViewModel.roll.observe(this){
            binding.studentRoll.text = String.format("roll no : %s", it )
        }
        setting.setOnClickListener {
            val intent = Intent(this, QueriesResolved::class.java)
            startActivity(intent)
        }
        phome.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
        infop.setOnClickListener {
            val intent = Intent(this, Info::class.java)
            startActivity(intent)
        }
        binding.logout.setOnClickListener{
            SharedPrefManager.getInstance(applicationContext).clear()
            val intent = Intent(this, HomePage::class.java)
            Toast.makeText(applicationContext,"Logout Initiated", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }

    }

    fun addQuery( rollno : String?, tkn : String?, cText: String, cDes: String, hcode:String){

        val apiService = RestApiService()

        val complaint = AddQuery(
            roll_no = rollno,
            token =tkn,
            complaint_text = cText,
            complaint_text_title = cDes,
            hostel_code = hcode,
            message = ""
        )

        apiService.addQueries(complaint) {
           if(it?.message.isNullOrEmpty())
               error("Failed adding a query")
        }

    }
////    fun addQueries(newQuery: Queries){
//
//    }
//
//    fun addQueries(newQuery: Queries){
//
//    }
}