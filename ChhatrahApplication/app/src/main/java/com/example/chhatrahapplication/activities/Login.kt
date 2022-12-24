package com.example.chhatrahapplication.activities

import com.example.chhatrahapplication.api.RestApiService
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.chhatrahapplication.R
import com.example.chhatrahapplication.SharedPrefManager
import com.example.chhatrahapplication.databinding.ActivityLoginBinding
import com.example.chhatrahapplication.models.User

class Login : AppCompatActivity() {

    private lateinit var login_btn: Button
    private lateinit var signup_btn: Button
    private lateinit var binding: ActivityLoginBinding

    @SuppressLint("SuspiciousIndentation", "RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        login_btn = findViewById(R.id.login_btn)
        signup_btn = findViewById(R.id.signup_btn)
        login_btn.setOnClickListener {
            val rollno = binding.editUsername.text.toString().trim()
            val password = binding.editPassword.text.toString().trim()

            if (rollno.isEmpty()) {
                binding.editUsername.error = "Roll No Required"
                binding.editUsername.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.editPassword.error = "Select Pass"
                binding.editPassword.requestFocus()
                return@setOnClickListener
            }

            val user = User(
                roll_no = rollno,
                password = password,
                token = ""
            )
            loginFunction(rollno,password)
        }
            signup_btn.setOnClickListener {
                val intent = Intent(this, Signup::class.java)
                startActivity(intent)
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
            if (!it?.token.isNullOrEmpty()) {
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
    override fun onStart() {
        super.onStart()

        if (SharedPrefManager.getInstance(this).isLoggedIn) {
            val intent = Intent(this, Profile::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }
    }
}