package Dokter.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.databinding.ActivitySignUpDokterBinding

class SignUp_Dokter : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySignUpDokterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpDokterBinding.inflate(layoutInflater)
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
        when(v.id){
            R.id.btnRegister -> {
                var Daftar = Intent(this@SignUp_Dokter, Login_Dokter::class.java)
                startActivity(Daftar)
            }
        }
        when(v.id){
            R.id.txtLogin -> {
                var Masuk = Intent(this@SignUp_Dokter,Login_Dokter::class.java)
                startActivity(Masuk)
            }
        }
    }
}