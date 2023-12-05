package com.collaboracrew.catwell.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.collaboracrew.catwell.DoctorMainActivity
import com.collaboracrew.catwell.R

class Room_Vidcall : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_vidcall)

        var btSelesai = findViewById<ImageButton>(R.id.btSelesai)
        btSelesai.setOnClickListener {
            var intent = Intent(this@Room_Vidcall, DoctorMainActivity::class.java)
            intent.putExtra("listChat", true)
            startActivity(intent)

        }
    }


}