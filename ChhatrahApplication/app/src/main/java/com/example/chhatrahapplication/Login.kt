package com.example.chhatrahapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Login : AppCompatActivity() {

    private lateinit var login_btn : Button
    private lateinit var signup_btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_btn = findViewById(R.id.login_btn)
        signup_btn = findViewById(R.id.signup_btn)

        login_btn.setOnClickListener {
            val intent = Intent(this, profile :: class.java)
            startActivity(intent)
        }
        signup_btn.setOnClickListener {
            val intent = Intent( this, Signup :: class.java)
            startActivity(intent)
        }



    }
}