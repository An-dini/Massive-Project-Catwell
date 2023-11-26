package com.collaboracrew.catwell.view

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import com.collaboracrew.catwell.R
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Edit_Profil_Kucing : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var imageButton: ImageButton
    private lateinit var button: Button
    private lateinit var floatingActionButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profil_kucing)

        imageView = findViewById(R.id.fotoprofilekucing)
        imageButton = findViewById(R.id.ivBack)
        button = findViewById(R.id.btn_edt_profil)
        floatingActionButton = findViewById(R.id.floatingActionButton2)

        var btn_edt_profil = findViewById<Button>(R.id.btn_edt_profil)
        btn_edt_profil.setOnClickListener {
            var intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }


        val backButton = findViewById(R.id.ivBack) as ImageView

        backButton.setOnClickListener {
            onBackPressed()
        }


        floatingActionButton.setOnClickListener{
            ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }
        }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imageView.setImageURI(data?.data)



    }
}