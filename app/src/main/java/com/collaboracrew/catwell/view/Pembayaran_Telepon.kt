package com.collaboracrew.catwell.view

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import com.collaboracrew.catwell.R

class Pembayaran_Telepon : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran_telepon)

        val btpay: Button = findViewById(R.id.BtnBayarVidcall)
        btpay.setOnClickListener {
            successDialog()
        }

        val backButton = findViewById(R.id.ivBack) as ImageView

        backButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun successDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_doctor_ready)

        val btnDialog: Button = dialog.findViewById(R.id.btMasuk)
        btnDialog.setOnClickListener {
            val intent = Intent(this@Pembayaran_Telepon, ChatLogActivity::class.java)
            intent.putExtra("startConsultation", true)
            startActivity(intent)
        }
        dialog.show()
    }
}