package com.example.chhatrahapplication.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chhatrahapplication.R
import com.example.chhatrahapplication.SharedPrefManager
import com.example.chhatrahapplication.api.RestApiService
import com.example.chhatrahapplication.databinding.ActivitySignupBinding
import com.example.chhatrahapplication.models.DefaultResponse
import com.example.chhatrahapplication.models.User


class Signup : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val dropdown = findViewById<Spinner>(R.id.spinner1)
//create a list of items for the spinner.
//create a list of items for the spinner.
        val items = arrayOf("Choose Hostel","Hostel 1 "," Hostel 2","Hostel 3", "Hostel 4", "Hostel 5")
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
//set the spinners adapter to the previously created one.
//set the spinners adapter to the previously created one.
        dropdown.adapter = adapter

       binding.signupBtn.setOnClickListener {


           val fname = binding.editFname.text.toString().trim()
           val mname = binding.editMname.text.toString().trim()
           val lname = binding.editLname.text.toString().trim()
           val name = String.format(fname+" "+mname+" "+lname)
           val rollno = binding.editRoll.text.toString().trim()
           val hname = binding.spinner1.selectedItem.toString()
           val hcode= items.indexOf(hname)
           val password = binding.editSpass.text.toString().trim()

           if (fname.isEmpty()){
               binding.editFname.error = "First name required"
               binding.editFname.requestFocus()
               return@setOnClickListener
           }
           if (lname.isEmpty()){
               binding.editLname.error = "Last name required"
               binding.editLname.requestFocus()
               return@setOnClickListener
           }
           if (rollno.isEmpty()){
               binding.editRoll.error = "Roll No Required"
               binding.editRoll.requestFocus()
               return@setOnClickListener
           }

           if (password.isEmpty()){
               binding.editSpass.error = "Select Pass"
               binding.editSpass.requestFocus()
               return@setOnClickListener
           }
           val hostelName = arrayOf(" ","AA","BB","CC","DD","EE")
           val apiService = RestApiService()
           val student = DefaultResponse(
               roll_no = rollno,
               password = password,
               name = name,
               hostel_code = hostelName.get(hcode),
               message = ""
           )
           apiService.addUser(student) {
               if (!it?.message.isNullOrEmpty()) {
                   Toast.makeText(applicationContext, "Sucsessfull signUp", Toast.LENGTH_LONG).show()
                   loginFunction(rollno,password)

               } else {
                   Toast.makeText(applicationContext, "Invalid Details", Toast.LENGTH_LONG)
                       .show()
               }


           }


       }


        }
    private fun loginFunction(rollno:String,password:String){

        val apiService = RestApiService()
        val user = User(
            roll_no = rollno,
            password = password,
            token = ""
        )
        apiService.loginUser(user) {
            if (it?.token != "") {
                val tkn = it?.token.toString().trim()
                Toast.makeText(applicationContext,tkn, Toast.LENGTH_LONG).show()
                Toast.makeText(applicationContext, "Sucsessfull login", Toast.LENGTH_LONG).show()

                SharedPrefManager.getInstance(applicationContext).saveUser(tkn,rollno,password)

                val intent = Intent(applicationContext, Profile::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "Invalid Credential", Toast.LENGTH_LONG)
                    .show()
            }


        }

    }












        }


