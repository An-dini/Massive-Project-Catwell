package com.collaboracrew.catwell.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.collaboracrew.catwell.MainActivity
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.databinding.ActivityLoginBinding

class Login : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val txtviewsignup: TextView = findViewById(R.id.txt_signup)
        txtviewsignup.setOnClickListener(this)

        val txtForgotPass: TextView = findViewById(R.id.txt_forgot)
        txtForgotPass.setOnClickListener(this)

        val txtViewSignIn: Button = findViewById(R.id.btnLogin)
        txtViewSignIn.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.txt_signup -> {
                val intent = Intent(this@Login, register::class.java)
                startActivity(intent)
            }

            R.id.txt_forgot -> {
                val intent = Intent(this@Login, ForgotPassword::class.java)
                startActivity(intent)
            }

            R.id.btnLogin -> {
                val intent = Intent(this@Login, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
