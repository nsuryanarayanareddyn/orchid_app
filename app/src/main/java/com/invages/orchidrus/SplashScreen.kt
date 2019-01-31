package com.invages.orchidrus

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.invages.orchidrus.screens.LoginScreen

class SplashScreen : AppCompatActivity() {

    private val splashDelay = 2L;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        Handler().postDelayed({
            kotlin.run {
                startActivity(Intent(this, LoginScreen::class.java))
                finish()
            }
        }, splashDelay)
    }
}