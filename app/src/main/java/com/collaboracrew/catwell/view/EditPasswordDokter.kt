package com.collaboracrew.catwell.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.collaboracrew.catwell.DoctorMainActivity
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.view.ProfileActivity

class EditPasswordDokter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_password_dokter)

        var btnSimpan = findViewById<Button>(R.id.btnSimpan)
        btnSimpan.setOnClickListener {
            var intent = Intent(this@EditPasswordDokter, DoctorMainActivity::class.java)
            intent.putExtra("simpanProfil", true)
            startActivity(intent)
        }

        val backButton = findViewById(R.id.ivBack) as ImageView

        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}