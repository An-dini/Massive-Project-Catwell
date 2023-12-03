package Dokter.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.collaboracrew.catwell.R

class SignUp_Dokter : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_dokter)

        var BtnDaftar:Button = findViewById(R.id.btnRegister)
        BtnDaftar.setOnClickListener(this)

        var TxtViewMsk: TextView = findViewById(R.id.txtLogin)
        TxtViewMsk.setOnClickListener(this)
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