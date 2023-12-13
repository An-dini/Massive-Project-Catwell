package com.collaboracrew.catwell.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.telecom.Call
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.tracing.perfetto.handshake.protocol.Response
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.api.ApiRetrofit
import com.collaboracrew.catwell.databinding.ActivityRegisterBinding
import com.collaboracrew.catwell.model.SubmitModel
import com.google.android.material.textfield.TextInputLayout
import org.chromium.base.Callback
import retrofit2.Call
import retrofit2.Call as RetrofitCall
import okhttp3.Call as OkHttpCall


class register : AppCompatActivity() {
    private val api by lazy { ApiRetrofit().endpoint }
    private lateinit var btnregister: Button
    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    private lateinit var etGender: Spinner
    private lateinit var tipePengguna: TextView
    private lateinit var etConfirmPass: EditText
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val genderOptions = resources.getStringArray(R.array.gender_options)
        val adapter = ArrayAdapter(this, R.layout.dropdown_item, genderOptions)
        binding.autoCompleteGender.setAdapter(adapter)

        val txtviewlogin:TextView = binding.txtLogin
        txtviewlogin.setOnClickListener{
            startActivity(Intent(this@register, Login::class.java))
            finish()
        }

        btnregister = binding.btnRegister
        etName = binding.edtFullname
        etEmail = binding.edtEmail
        etGender = binding.autoCompleteGender
        etPass = binding.edtEnteepasswd
        tipePengguna = binding.tipePengguna
        etConfirmPass = binding.edtConfirmpasswd

        tipePengguna.text = "User"

        setupListener()
    }

    private fun setupListener(){
        btnregister.setOnClickListener {
            if (etName.text.toString().isNotEmpty() || etEmail.text.toString().isNotEmpty() || etPass.text.toString().isNotEmpty() || etConfirmPass.text.toString().isNotEmpty() || etGender.selectedItem.toString().isNotEmpty()){
                if (etPass.text.toString() == etConfirmPass.text.toString()) {
                    Log.e("RegisterActivity", etName.text.toString())
                    api.create(etName.text.toString(),etEmail.text.toString(),etPass.text.toString(),etGender.selectedItem.toString(),tipePengguna.text.toString())
                        .enqueue(object : retrofit2.Callback<SubmitModel> {
                            override fun onFailure(call: retrofit2.Call<SubmitModel>, t: Throwable) {
                                Log.e("RegisterActivity", t.toString())
                            }

                            override fun onResponse(call: Call<SubmitModel>, response: retrofit2.Response<SubmitModel>) {
                                if (response.isSuccessful){
//                                    Log.e("RegisterActivity", response.toString())
                                    startActivity(Intent(this@register, Login::class.java))
                                    finish()
                                }
                            }
                        })
                } else {
                    Toast.makeText(applicationContext, "Password tidak sama", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(applicationContext, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}