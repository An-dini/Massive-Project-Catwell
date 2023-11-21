package com.collaboracrew.catwell.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.collaboracrew.catwell.R

class WelcomeScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)

        val btDaftar = findViewById(R.id.btDaftar) as Button
        btDaftar.setOnClickListener {
            val intent = Intent(this, register::class.java)
            startActivity(intent)
        }

        val btMasuk = findViewById(R.id.btMasuk) as Button
        btMasuk.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}