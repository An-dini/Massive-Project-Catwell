package com.collaboracrew.catwell.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.collaboracrew.catwell.R
import com.google.android.material.snackbar.Snackbar

class Detail_Psw_Ragunan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_psw_ragunan)

        val buttonMaps = findViewById<Button>(R.id.BtnMaps)
        buttonMaps.setOnClickListener {
            val namaPswTextView = findViewById<TextView>(R.id.NamaPsw)
            val destination = namaPswTextView.text.toString()

            val gmmIntentUri = Uri.parse("https://www.google.com/maps/dir//Puskeswan,+Jl.+Harsono+RM+No.36-28,+RT.9%2FRW.4,+Ragunan,+Ps.+Minggu,+Kota+Jakarta+Selatan,+Daerah+Khusus+Ibukota+Jakarta+12550/@-6.2989833,106.818366,17z/data=!4m8!4m7!1m0!1m5!1m1!1s0x2e69edff80945163:0x28ae6e1f00d2fa77!2m2!1d106.8209409!2d-6.2989886?authuser=0&entry=ttu$destination")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")

            if (mapIntent.resolveActivity(packageManager) != null) {
                startActivity(mapIntent)
            } else {
                val rootView = findViewById<View>(android.R.id.content)
                Snackbar.make(rootView, "Aplikasi Google Maps tidak ditemukan", Snackbar.LENGTH_SHORT).show()
            }
        }

        val ivBack = findViewById<ImageView>(R.id.ivBack)
        ivBack.setOnClickListener {
            onBackPressed()
        }
    }
}