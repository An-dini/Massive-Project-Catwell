package com.collaboracrew.catwell.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.collaboracrew.catwell.R

class register : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnregister: Button = findViewById(R.id.btnRegister)
        btnregister.setOnClickListener(this)

        val txtviewlogin:TextView =findViewById(R.id.txtLogin)
        txtviewlogin.setOnClickListener(this)
    }
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnRegister -> {
                val intent = Intent(this@register, Login::class.java)
                startActivity(intent)
            }
        }
        when (v.id) {
            R.id.txtLogin -> {
                val intent = Intent(this@register, Login::class.java)
                startActivity(intent)
            }
        }
    }
}

