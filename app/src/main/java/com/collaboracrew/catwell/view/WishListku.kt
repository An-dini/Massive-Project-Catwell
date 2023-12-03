package com.collaboracrew.catwell.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.collaboracrew.catwell.R

class WishListku : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wish_listku)

        val backButton = findViewById(R.id.ivBack) as ImageView
        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}