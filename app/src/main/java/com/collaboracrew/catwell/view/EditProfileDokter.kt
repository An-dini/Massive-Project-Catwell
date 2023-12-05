package com.collaboracrew.catwell.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import com.collaboracrew.catwell.DoctorMainActivity
import com.collaboracrew.catwell.R

class EditProfileDokter : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile_dokter)

        var btn_edt_profil_dk= findViewById<Button>(R.id.btn_edt_profil_dk)
        btn_edt_profil_dk.setOnClickListener {
            var intent = Intent(this@EditProfileDokter, DoctorMainActivity::class.java)
            intent.putExtra("simpanProfil", true)
            startActivity(intent)
        }

        val backButton = findViewById(R.id.ivBack) as ImageView

        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}