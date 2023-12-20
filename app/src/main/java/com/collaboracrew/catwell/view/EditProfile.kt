package com.collaboracrew.catwell.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.api.ApiRetrofit
import com.collaboracrew.catwell.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfile : AppCompatActivity() {
    private val api by lazy { ApiRetrofit().endpoint }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val btn_edt_profil = findViewById<Button>(R.id.btn_edt_profil)
        btn_edt_profil.setOnClickListener {
            val intent = Intent(this, Edit_Profil_Kucing::class.java)
            startActivity(intent)
        }

        val backButton = findViewById(R.id.ivBack) as ImageView

        backButton.setOnClickListener {
            onBackPressed()
        }

        getUser()
    }

    private fun getUser(){
        val receivedEmail = intent.getStringExtra("email")
        val receivedId = intent.getStringExtra("idUser")
        Log.e("id user", "$receivedId")

        val tvNama = findViewById<TextView>(R.id.edtnama)
        val tvEmail = findViewById<TextView>(R.id.edt_email)
        val tvNo = findViewById<TextView>(R.id.edt_nohp)
        val btEdit = findViewById<Button>(R.id.btn_edt_profil)


        btEdit.setOnClickListener {
            api.updateUser(
                receivedId.toString(),
                tvNama.text.toString(),
                tvEmail.text.toString(),
                tvNo.text.toString()
            ).enqueue(object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.e("EditProfile", t.toString())
                    Toast.makeText(this@EditProfile, "Data berhasil diupdate!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@EditProfile, ProfileActivity::class.java))
                    finish()
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        Log.e("EditProfile", "Success to update user data: ${response.code()}")
                        startActivity(Intent(this@EditProfile, ProfileActivity::class.java))
                        finish()
                    } else {
                        Log.e("EditProfile", "Failed to update user data: ${response.code()}")
                    }
                }
            })
        }

        api.dataUser(Email_User = receivedEmail.toString()).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("ProfileActivity", t.toString())
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()?.user
                    if (user != null && user.isNotEmpty()) {
                        val userData = user[0]
                        tvNama.text = userData.Nama_User
                        tvEmail.text = userData.Email_User
                        tvNo.text = userData.No_User
                    } else {
                        Log.e("ProfileActivity", "No user data found")
                    }
                } else {
                    Log.e("ProfileActivity", "Failed to retrieve user data: ${response.code()}")
                }
            }
        })
    }
}