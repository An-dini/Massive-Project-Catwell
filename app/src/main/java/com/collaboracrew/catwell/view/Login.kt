package com.collaboracrew.catwell.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.collaboracrew.catwell.MainActivity
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.api.ApiRetrofit
import com.collaboracrew.catwell.databinding.ActivityLoginBinding
import com.collaboracrew.catwell.model.LoginModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity(){

    private lateinit var binding: ActivityLoginBinding
    private val api by lazy { ApiRetrofit().endpoint }
    private lateinit var btLogin: Button
    private lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    private lateinit var tipePengguna: TextView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val txtviewsignup: TextView = findViewById(R.id.txt_signup)
        txtviewsignup.setOnClickListener {
            startActivity(Intent(this@Login, register::class.java))
            finish()
        }

        val txtForgotPass: TextView = findViewById(R.id.txt_forgot)
        txtForgotPass.setOnClickListener {
            startActivity(Intent(this@Login, ForgotPassword::class.java))
            finish()
        }

        sharedPreferences = getSharedPreferences("CatWellPref", Context.MODE_PRIVATE)

        setupView()
        setupListener()
    }

    private fun setupView() {
        btLogin = findViewById(R.id.btnLogin)
        etEmail = findViewById(R.id.email)
        etPass = findViewById(R.id.passwd)
        tipePengguna = binding.tipePengguna
        tipePengguna.text = "User"
    }

    private fun setupListener() {
        btLogin.setOnClickListener {
            val emailUser = etEmail.text.toString()
            val passUser = etPass.text.toString()
            val tipePengguna = tipePengguna.text.toString()

            if (emailUser.isNotEmpty() && passUser.isNotEmpty()) {
                Log.e("LoginActivity", emailUser)
                api.login(emailUser, passUser, tipePengguna)
                    .enqueue(object : Callback<LoginModel> {
                        override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                            Log.e("LoginActivity", t.toString())
                            Toast.makeText(
                                applicationContext,
                                "Login failed: ${t.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onResponse(
                            call: Call<LoginModel>,
                            response: Response<LoginModel>
                        ) {
                            if (response.isSuccessful) {
                                val loginResponse = response.body()

                                if (loginResponse?.success == true) {
                                    val ID_User = loginResponse.ID_User

                                    val editor = sharedPreferences.edit()
                                    editor.putBoolean("isLoggedIn", true)
                                    editor.putInt("userId", ID_User)
                                    editor.apply()

                                    startActivity(
                                        Intent(
                                            this@Login,
                                            MainActivity::class.java
                                        )
                                    )
                                    finish()
                                } else {
                                    Toast.makeText(
                                        applicationContext,
                                        "Invalid email or password",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else {
                                val errorResponse = response.errorBody()?.string()
                                Toast.makeText(
                                    applicationContext,
                                    "Login failed: $errorResponse",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
            } else {
                Toast.makeText(
                    applicationContext,
                    "Data Tidak Boleh Kosong!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
