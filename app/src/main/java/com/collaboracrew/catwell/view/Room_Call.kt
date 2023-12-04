package com.collaboracrew.catwell.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.collaboracrew.catwell.R

class Room_Call : AppCompatActivity(), View.OnClickListener {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_call)

        var Klik: ImageButton = findViewById(R.id.Btn_Selesai)
        Klik.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.Btn_Selesai -> {
                var Tekan = Intent(this@Room_Call, HistoryConsultationDetailActivity::class.java)
                startActivity(Tekan)
            }
        }
    }
}