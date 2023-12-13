package com.collaboracrew.catwell.view

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.collaboracrew.catwell.R

class ProfileDokter: AppCompatActivity(), View.OnClickListener{
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_dokter)

        sharedPreferences = getSharedPreferences("CatWellPref", Context.MODE_PRIVATE)

        val edtprofil_dk: TextView = findViewById(R.id.editProfile_dk)
        edtprofil_dk.setOnClickListener(this)
        val edtpasswd_dk: TextView = findViewById(R.id.edtPass_dk)
        edtpasswd_dk.setOnClickListener(this)
        val ttgapk_dk: TextView = findViewById(R.id.ttgapk_dk)
        ttgapk_dk.setOnClickListener(this)
        val keluar_dk: TextView = findViewById(R.id.keluar_dk)
        keluar_dk.setOnClickListener(this)
        val hpsakun_dk: TextView = findViewById(R.id.hps_akn_dk)
        hpsakun_dk.setOnClickListener(this)

        val backButton = findViewById(R.id.ivBack) as ImageView

        backButton.setOnClickListener {
            onBackPressed()
        }

    }
    override fun onClick(v: View) {
        when (v.id) {
            R.id.editProfile_dk -> {
                val intent = Intent(this@ProfileDokter, EditProfileDokter::class.java)
                startActivity(intent)
            }
        }
        when (v.id) {
            R.id.edtPass_dk-> {
                val intent = Intent(this@ProfileDokter, EditPasswordDokter::class.java)
                startActivity(intent)
            }
        }
        when (v.id) {
            R.id.keluar_dk-> {
                LogoutAccountDialog()
            }
        }
        when (v.id) {
            R.id.ttgapk_dk-> {
                val intent = Intent(this@ProfileDokter, TentangAplikasi::class.java)
                startActivity(intent)
            }
        }
        when (v.id) {
            R.id.hps_akn_dk-> {
                deleteAccountDialog()
            }
        }
    }

    private  fun  deleteAccountDialog(){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_hapus_akun)

        val btYa: Button = dialog.findViewById(R.id.btYa)
        btYa.setOnClickListener {
            val intent = Intent(this@ProfileDokter, Pilih_Sebagai::class.java)
            startActivity(intent)
        }

        val btTidak: Button = dialog.findViewById(R.id.btTidak)
        btTidak.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private  fun  LogoutAccountDialog(){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_logout)

        val btYa: Button = dialog.findViewById(R.id.btYa)
        btYa.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.remove("isDoctorLoggedIn")
            editor.apply()

            val intent = Intent(this@ProfileDokter, Pilih_Sebagai::class.java)
            startActivity(intent)
            finish()
        }

        val btTidak: Button = dialog.findViewById(R.id.btTidak)
        btTidak.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

}