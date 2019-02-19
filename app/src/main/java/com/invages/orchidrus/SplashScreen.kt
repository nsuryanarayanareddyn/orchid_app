package com.invages.orchidrus

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.invages.orchidrus.screens.HomeScreen
import com.invages.orchidrus.screens.LoginScreen
import com.invages.orchidrus.util.Utils

class SplashScreen : AppCompatActivity() {

    private val splashDelay = 2L;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        Handler().postDelayed({
            kotlin.run {

                //      fortesting
                //Utils.setPreferenceValue(this, "token", "kjjhjhg")

                val token: String? = Utils.getPreferenceValue(this, "token")

                if (token == "" || token == "null")
                    startActivity(Intent(this, LoginScreen::class.java))
                else
                    startActivity(Intent(this, HomeScreen::class.java))

                finish()
            }
        }, splashDelay)
    }
}