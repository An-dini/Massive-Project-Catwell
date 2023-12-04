package com.collaboracrew.catwell.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.collaboracrew.catwell.MainActivity
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.databinding.ActivityLoginBinding
import com.collaboracrew.catwell.model.UserModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.security.MessageDigest

class Login : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("Users")

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
                val signInEmail = binding.username.text.toString()
                val signInPassword = binding.passwd.text.toString()

                if (signInEmail.isNotEmpty() && signInPassword.isNotEmpty()) {
                    loginUser(signInEmail, signInPassword)
                } else {
                    Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun hashPassword(password: String): String {
        val bytes = password.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }

    private fun loginUser(email: String, password: String) {
        databaseReference.orderByChild("email_user").equalTo(email)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (userSnapshot in dataSnapshot.children) {
                            val userModel = userSnapshot.getValue(UserModel::class.java)

                            if (userModel != null && userModel.password == hashPassword(password)) {
                                Toast.makeText(this@Login, "Login Successful", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@Login, MainActivity::class.java))
                                finish()
                                return
                            }
                        }
                    }
                    Toast.makeText(this@Login, "Login Failed", Toast.LENGTH_SHORT).show()
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(
                        this@Login,
                        "Database Error : ${databaseError.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}
