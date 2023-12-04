package Dokter.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.collaboracrew.catwell.R

class Login_Dokter : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_dokter)

        var TextDaftar:TextView = findViewById(R.id.txt_signup)
        TextDaftar.setOnClickListener(this)

        var TextLupaPw:TextView = findViewById(R.id.txt_forgot)
        TextLupaPw.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.txt_signup -> {
                var Daftar = Intent(this@Login_Dokter, SignUp_Dokter::class.java)
                startActivity(Daftar)
            }
        }
        when(v.id){
            R.id.txt_forgot -> {
                var PW = Intent(this@Login_Dokter, LupaPasswordDokter::class.java)
                startActivity(PW)
            }
        }
    }
}