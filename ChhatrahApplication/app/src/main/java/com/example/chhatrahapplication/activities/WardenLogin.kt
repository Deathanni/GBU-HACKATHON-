package com.example.chhatrahapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.chhatrahapplication.R
import com.example.chhatrahapplication.SharedPrefManager
import com.example.chhatrahapplication.api.RestApiService
import com.example.chhatrahapplication.api.RetrofitClient
import com.example.chhatrahapplication.databinding.ActivityLoginBinding
import com.example.chhatrahapplication.databinding.ActivityWardenLoginBinding
import com.example.chhatrahapplication.models.LoginResponse
import com.example.chhatrahapplication.models.User
import com.example.chhatrahapplication.models.Warden
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WardenLogin : AppCompatActivity() {
    private lateinit var login_btn : Button
    private lateinit var binding: ActivityWardenLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityWardenLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        login_btn = findViewById(R.id.login_btn)

        login_btn.setOnClickListener {
            val username = binding.editUsername.text.toString().trim()
            val password = binding.editPassword.text.toString().trim()

            if (username.isEmpty()){
                binding.editUsername.error = "username No Required"
                binding.editUsername.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()){
                binding.editPassword.error = "Select Pass"
                binding.editPassword.requestFocus()
                return@setOnClickListener
            }
            val apiService = RestApiService()
            val warden = Warden(
                username = username,
                password = password,
                token = ""
            )
            apiService.loginAdmin(warden) {
                if (it?.token != "") {
                    val tkn = it?.token.toString().trim()
                    Toast.makeText(applicationContext,tkn, Toast.LENGTH_LONG).show()
                    Toast.makeText(applicationContext, "Sucsessfull login", Toast.LENGTH_LONG).show()

                    SharedPrefManager.getInstance(applicationContext).saveUser(username,tkn,password)

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

    }
