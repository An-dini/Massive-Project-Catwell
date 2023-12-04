package Dokter.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.collaboracrew.catwell.R

class LupaPasswordDokter : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lupa_password_dokter)

        var Password:Button = findViewById(R.id.btnSimpan)
        Password.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btnSimpan -> {
                var Simpan = Intent(this@LupaPasswordDokter, Login_Dokter::class.java)
                startActivity(Simpan)
            }
        }
    }
}