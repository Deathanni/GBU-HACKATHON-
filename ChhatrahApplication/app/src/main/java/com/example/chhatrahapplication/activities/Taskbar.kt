package com.example.chhatrahapplication.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.chhatrahapplication.R

class Taskbar : AppCompatActivity() {

    private lateinit var setting : ImageButton
    private lateinit var phome : ImageButton
    private lateinit var infop : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wardenactivity)

        setting = findViewById(R.id.setting)
        phome = findViewById(R.id.phome)
        infop = findViewById(R.id.info)

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
    }

}


