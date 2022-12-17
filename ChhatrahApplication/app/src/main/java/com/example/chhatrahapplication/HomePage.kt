package com.example.chhatrahapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton

class HomePage : AppCompatActivity() {

    private lateinit var openMenu: ImageButton
    private lateinit var btnStudent: Button
    private lateinit var btnWarden: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        openMenu = findViewById(R.id.btnOpen)
        btnStudent = findViewById(R.id.btnStudent)
        btnWarden = findViewById(R.id.btnWarden)

        openMenu.setOnClickListener{
            val intent = Intent(this,Menu::class.java )
            startActivity(intent)
        }
        btnStudent.setOnClickListener{
            val intent = Intent(this,Login::class.java )
            startActivity(intent)
        }
        btnWarden.setOnClickListener{
            val intent = Intent(this,WardenLogin::class.java )
            startActivity(intent)
        }






    }
}