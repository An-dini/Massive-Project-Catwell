package com.collaboracrew.catwell.view

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.collaboracrew.catwell.R

class JadwalKonsultasiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal_konsultasi)

        val backButton: ImageView = findViewById(R.id.ivBack)
        backButton.setOnClickListener {
            onBackPressed()
        }

        if (savedInstanceState == null) {
            replaceFragment(UpcomingConsultationFragment())
        }

        val btMendatang: Button = findViewById(R.id.btMendatang)
        val btTerlewat: Button = findViewById(R.id.btTerlewat)

        btMendatang.setOnClickListener {
            replaceFragment(UpcomingConsultationFragment())

            btMendatang.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white))
            btMendatang.setTextAppearance(R.style.poppinsSemiBold16spOrange)

            btTerlewat.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.PrimaryColor))
            btTerlewat.setTextAppearance(R.style.poppinsSemiBold16spWhite)
        }
        btTerlewat.setOnClickListener {
            replaceFragment(PostConsultationFragment())

            btMendatang.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.PrimaryColor))
            btMendatang.setTextAppearance(R.style.poppinsSemiBold16spWhite)

            btTerlewat.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white))
            btTerlewat.setTextAppearance(R.style.poppinsSemiBold16spOrange)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        Log.d("JadwalKonsultasiActivity", "Mengganti fragment dengan ${fragment::class.java.simpleName}")
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }
}
