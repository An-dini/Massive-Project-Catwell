package Dokter.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.collaboracrew.catwell.R

class WelcomeScreen_Dokter : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen_dokter)

        var BtnMasuk: Button = findViewById(R.id.btMasuk)
        BtnMasuk.setOnClickListener(this)

        var SignUp: Button = findViewById(R.id.btDaftar)
        SignUp.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btMasuk -> {
                var Klik = Intent(this@WelcomeScreen_Dokter, Login_Dokter::class.java)
                startActivity(Klik)
            }
        }
        when(v.id){
            R.id.btDaftar -> {
                var Klik = Intent(this@WelcomeScreen_Dokter, SignUp_Dokter::class.java)
                startActivity(Klik)
            }
        }
    }
}