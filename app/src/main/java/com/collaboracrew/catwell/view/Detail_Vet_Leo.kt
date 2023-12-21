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

class Detail_Vet_Leo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_vet_leo)

        val buttonMaps = findViewById<Button>(R.id.BtnMaps)
        buttonMaps.setOnClickListener {
            val namaPswTextView = findViewById<TextView>(R.id.NamaPsw)
            val destination = namaPswTextView.text.toString()

            val gmmIntentUri = Uri.parse("https://www.google.com/maps/dir//Praktek+Dokter+Hewan+Leo+N+Vets+Simpangan+Depok,+Jalan+Tole+Iskandar+Rt+02%2F20+no+24,+Sukamaju,+Kec.+Cilodong,+Kota+Depok,+Jawa+Barat+16412/@-6.4020562,106.5691062,11z/data=!4m8!4m7!1m0!1m5!1m1!1s0x2e69e99a7af5cdb7:0xfdab382fcda22d4f!2m2!1d106.8609785!2d-6.4101175?authuser=0&entry=ttu$destination")
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