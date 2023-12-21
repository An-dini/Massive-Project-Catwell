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

class Detail_Vet_Amore : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_vet_amore)


        val buttonMaps = findViewById<Button>(R.id.BtnMaps)
        buttonMaps.setOnClickListener {
            val namaPswTextView = findViewById<TextView>(R.id.NamaPsw)
            val destination = namaPswTextView.text.toString()

            val gmmIntentUri = Uri.parse("https://www.google.com/maps/dir//Amore+Sawangan,+Ruko+Muchtar+Center,+Blk.+E-F+Jl.+Raya+Muchtar,+Sawangan+Lama,+Kec.+Sawangan,+Kota+Depok,+Jawa+Barat+16511/@-6.4025107,106.6927244,13z/data=!4m8!4m7!1m0!1m5!1m1!1s0x2e69e8eae36349ef:0xba4459e60d4b9c9f!2m2!1d106.7579557!2d-6.405496?authuser=0&entry=ttu$destination")
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
