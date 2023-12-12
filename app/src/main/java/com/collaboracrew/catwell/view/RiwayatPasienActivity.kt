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

class RiwayatPasienActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat_pasien)

        val backButton: ImageView = findViewById(R.id.ivBack)
        backButton.setOnClickListener {
            onBackPressed()
        }

        if (savedInstanceState == null) {
            replaceFragment(AcceptConsultationFragment())
        }

        val btAccept: Button = findViewById(R.id.btAccept)
        val btReject: Button = findViewById(R.id.btReject)

        btAccept.setOnClickListener {
            replaceFragment(AcceptConsultationFragment())

            btAccept.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white))
            btAccept.setTextAppearance(R.style.poppinsSemiBold16spOrange)

            btReject.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.PrimaryColor))
            btReject.setTextAppearance(R.style.poppinsSemiBold16spWhite)
        }
        btReject.setOnClickListener {
            replaceFragment(RejectConsultationFragment())

            btAccept.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.PrimaryColor))
            btAccept.setTextAppearance(R.style.poppinsSemiBold16spWhite)

            btReject.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white))
            btReject.setTextAppearance(R.style.poppinsSemiBold16spOrange)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        Log.d("RiwayatPasienActivity", "Mengganti fragment dengan ${fragment::class.java.simpleName}")
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }
}
