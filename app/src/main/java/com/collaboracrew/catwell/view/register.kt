package com.collaboracrew.catwell.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.databinding.ActivityRegisterBinding
import java.security.MessageDigest

class register : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val genderOptions = resources.getStringArray(R.array.gender_options)
        val adapter = ArrayAdapter(this, R.layout.dropdown_item, genderOptions)
        binding.autoCompleteGender.setAdapter(adapter)

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

    fun hashPassword(password: String): String {
        val bytes = password.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }
}

