package com.collaboracrew.catwell.view

import Dokter.view.WelcomeScreen_Dokter
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.collaboracrew.catwell.DoctorMainActivity
import com.collaboracrew.catwell.MainActivity
import com.collaboracrew.catwell.R

class Pilih_Sebagai : AppCompatActivity(), View.OnClickListener {
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_sebagai)

        val Klik : Button = findViewById(R.id.BtnUser)
        Klik.setOnClickListener(this)

        val BtnDokter: Button = findViewById(R.id.BtnDokter)
        BtnDokter.setOnClickListener(this)

        sharedPreferences = getSharedPreferences("CatWellPref", Context.MODE_PRIVATE)

        if (sharedPreferences.contains("isLoggedIn")) {
            val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

            if (isLoggedIn) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        if (sharedPreferences.contains("isDoctorLoggedIn")) {
            val isDoctorLoggedIn = sharedPreferences.getBoolean("isDoctorLoggedIn", false)

            if (isDoctorLoggedIn) {
                val intent = Intent(this, DoctorMainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.BtnUser -> {
                val Masuk = Intent(this@Pilih_Sebagai, WelcomeScreenActivity::class.java)
                startActivity(Masuk)
            }
            R.id.BtnDokter -> {
                val Masuk = Intent(this@Pilih_Sebagai,WelcomeScreen_Dokter::class.java)
                startActivity(Masuk)
            }
        }
    }
}