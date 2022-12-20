package com.example.chhatrahapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.chhatrahapplication.R

class Menu : AppCompatActivity() {

    private lateinit var home_btn: Button
    private lateinit var contact_btn: Button
    private lateinit var about_btn: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

            home_btn = findViewById(R.id.home_btn)
            about_btn = findViewById(R.id.about_btn)
            contact_btn = findViewById(R.id.contact_btn)

            home_btn.setOnClickListener {
                val intent = Intent( this , HomePage::class.java)
                startActivity(intent)
            }
             about_btn.setOnClickListener {
            val intent = Intent( this , AboutUs::class.java)
            startActivity(intent)
            }
             contact_btn.setOnClickListener {
            val intent = Intent( this , ContactUs::class.java)
            startActivity(intent)
            }


    }
}