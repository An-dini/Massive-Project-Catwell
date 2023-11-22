package com.collaboracrew.catwell.view

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.collaboracrew.catwell.R
import com.airbnb.lottie.LottieAnimationView
import com.collaboracrew.catwell.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val animationView = findViewById<LottieAnimationView>(R.id.animation_view)
        animationView.setAnimation("logo_animation.json")
        animationView.playAnimation()

        animationView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}

            override fun onAnimationEnd(animation: Animator) {
                startActivity(Intent(this@SplashScreenActivity, Pilih_Sebagai::class.java))
                finish()
            }

            override fun onAnimationCancel(animation: Animator) {}

            override fun onAnimationRepeat(animation: Animator) {}
        })
    }
}
