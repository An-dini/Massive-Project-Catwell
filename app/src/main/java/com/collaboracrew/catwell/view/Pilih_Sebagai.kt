package com.collaboracrew.catwell.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.collaboracrew.catwell.R

class Pilih_Sebagai : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_sebagai)

        var Klik: Button = findViewById(R.id.BtnUser)
        Klik.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.BtnUser -> {
                var Tekan = Intent(this@Pilih_Sebagai, WelcomeScreenActivity::class.java)
                startActivity(Tekan)
            }
        }
    }
}


