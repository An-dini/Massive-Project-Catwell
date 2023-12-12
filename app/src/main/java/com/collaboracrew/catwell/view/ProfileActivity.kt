package com.collaboracrew.catwell.view

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.collaboracrew.catwell.R

class ProfileActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val txtviewalmt: TextView = findViewById(R.id.profileKucing)
        txtviewalmt.setOnClickListener(this)
        val txtviedtpass: TextView = findViewById(R.id.edt_pass)
        txtviedtpass.setOnClickListener(this)
        val txtviedtlogout: TextView = findViewById(R.id.Keluar_user)
        txtviedtlogout.setOnClickListener(this)
        val txtviedtprofil: TextView = findViewById(R.id.editProfile)
        txtviedtprofil.setOnClickListener(this)
        val txtviabout: TextView = findViewById(R.id.ttgapk_user)
        txtviabout.setOnClickListener(this)
        val txtvihapusakn: TextView = findViewById(R.id.hps_akn)
        txtvihapusakn.setOnClickListener(this)

        val backButton = findViewById(R.id.ivBack) as ImageView

        backButton.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.profileKucing -> {
                val intent = Intent(this@ProfileActivity, EditProfileKucing::class.java)
                startActivity(intent)
            }
        }
        when (v.id) {
            R.id.edt_pass-> {
                val intent = Intent(this@ProfileActivity, EditPassword::class.java)
                startActivity(intent)
            }
        }
        when (v.id) {
            R.id.Keluar_user-> {
                LogoutAccountDialog()
            }
        }
        when (v.id) {
            R.id.editProfile-> {
                val intent = Intent(this@ProfileActivity, EditProfile::class.java)
                startActivity(intent)
            }
        }
        when (v.id) {
            R.id.ttgapk_user-> {
                val intent = Intent(this@ProfileActivity, TentangAplikasi::class.java)
                startActivity(intent)
            }
        }
        when (v.id) {
            R.id.hps_akn-> {
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
            val intent = Intent(this@ProfileActivity, Pilih_Sebagai::class.java)
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
            val intent = Intent(this@ProfileActivity, Pilih_Sebagai::class.java)
            startActivity(intent)
        }

        val btTidak: Button = dialog.findViewById(R.id.btTidak)
        btTidak.setOnClickListener {
            dialog.dismiss()
        }


        dialog.show()
    }

}