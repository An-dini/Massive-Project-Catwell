package com.collaboracrew.catwell.view

import Dokter.view.WelcomeScreen_Dokter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.collaboracrew.catwell.DoctorMainActivity
import com.collaboracrew.catwell.R

class Pilih_Sebagai : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_sebagai)

        var Klik : Button = findViewById(R.id.BtnUser)
        Klik.setOnClickListener(this)

        var BtnDokter: Button = findViewById(R.id.BtnDokter)
        BtnDokter.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.BtnUser -> {
                var Masuk = Intent(this@Pilih_Sebagai, WelcomeScreenActivity::class.java)
                startActivity(Masuk)
            }
        }
        when(v.id){
            R.id.BtnDokter -> {
                var Masuk = Intent(this@Pilih_Sebagai,WelcomeScreen_Dokter::class.java)
                startActivity(Masuk)
            }
            R.id.BtnDokter -> {
                var Tekan = Intent(this@Pilih_Sebagai, DoctorMainActivity::class.java)
                startActivity(Tekan)
            }

        }
    }
}