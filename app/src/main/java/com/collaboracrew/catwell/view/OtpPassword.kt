package com.collaboracrew.catwell.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.collaboracrew.catwell.R

class OtpPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_password)

        val btVerif = findViewById<Button>(R.id.btVerif)
        btVerif.setOnClickListener {
            var intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        val backButton = findViewById(R.id.ivBack) as ImageView

        backButton.setOnClickListener {
            onBackPressed()
        }

        val etOTP1 = findViewById<EditText>(R.id.etOTP1)
        val etOTP2 = findViewById<EditText>(R.id.etOTP2)
        val etOTP3 = findViewById<EditText>(R.id.etOTP3)
        val etOTP4 = findViewById<EditText>(R.id.etOTP4)

        val etOTP: List<EditText> = listOf(etOTP1, etOTP2, etOTP3, etOTP4)


        etOTP.forEachIndexed { index, editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1) {
                        val nextIndex = index + 1
                        if (nextIndex < etOTP.size) {
                            etOTP[nextIndex].requestFocus()
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }

    }
}