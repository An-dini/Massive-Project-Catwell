package com.collaboracrew.catwell.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.databinding.ActivityRegisterBinding
import com.collaboracrew.catwell.model.UserModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.security.MessageDigest

class register : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val genderOptions = resources.getStringArray(R.array.gender_options)
        val adapter = ArrayAdapter(this, R.layout.dropdown_item, genderOptions)
        binding.autoCompleteGender.setAdapter(adapter)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("Users")


        val btnregister: Button = findViewById(R.id.btnRegister)
        btnregister.setOnClickListener(this)

        val txtviewlogin:TextView =findViewById(R.id.txtLogin)
        txtviewlogin.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnRegister -> {
                val signupFullName = binding.edtFullname.text.toString()
                val signupGender = binding.autoCompleteGender.selectedItem.toString()
                val signupEmail = binding.edtEmail.text.toString()
                val signupPassword = binding.edtEnteepasswd.text.toString()
                val signupConfirmPassword = binding.edtConfirmpasswd.text.toString()

                if (signupFullName.isNotEmpty() && signupGender.isNotEmpty() && signupEmail.isNotEmpty() && signupPassword.isNotEmpty() && signupConfirmPassword.isNotEmpty()){
                    if (signupPassword == signupConfirmPassword) {
                        signupUser(signupFullName, signupPassword, signupGender, signupEmail, "user")
                    } else {
                        Toast.makeText(this@register, "Password dan Konfirmasi Password tidak cocok", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@register, "Data kamu kurang lengkap", Toast.LENGTH_SHORT).show()
                }
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

    private fun signupUser(nama_lengkap: String, password: String, jenis_kelamin: String, email_user: String, tipe_pengguna: String){
        val hashedPassword = hashPassword(password)

        databaseReference.orderByChild("email_user").equalTo(email_user).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (!dataSnapshot.exists()){
                    val id = databaseReference.push().key
                    val userModel = UserModel(id, nama_lengkap, jenis_kelamin, email_user, hashedPassword, tipe_pengguna)
                    databaseReference.child(id!!).setValue(userModel)
                    Toast.makeText(this@register, "Signup Successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@register, Login::class.java))
                    finish()
                } else {
                    Toast.makeText(this@register, "Email Sudah Terdaftar", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@register, "Database Error : ${databaseError.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

