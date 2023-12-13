package com.collaboracrew.catwell.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import com.collaboracrew.catwell.DoctorMainActivity
import com.collaboracrew.catwell.R
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EditProfileDokter : AppCompatActivity() {
    private lateinit var pickImageBtn:FloatingActionButton
    private lateinit var imageview:ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile_dokter)


        var btn_edt_profil_dk= findViewById<Button>(R.id.btn_edt_profil_dk)
        btn_edt_profil_dk.setOnClickListener {
            var intent = Intent(this@EditProfileDokter, DoctorMainActivity::class.java)
            intent.putExtra("simpanProfil", true)
            startActivity(intent)
        }

        val backButton = findViewById(R.id.ivBack) as ImageView

        backButton.setOnClickListener {
            onBackPressed()
        }
        pickImageBtn = findViewById(R.id.floatingActionButton2)
        imageview = findViewById(R.id.fotoprofile_dk)

        pickImageBtn.setOnClickListener {
            ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }
    }

    override fun onActivityResult (requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imageview.setImageURI(data?.data)
    }

}