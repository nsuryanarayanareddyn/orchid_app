package com.invages.orchidrus

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.invages.orchidrus.screens.HomeScreen
import com.invages.orchidrus.screens.LoginScreen
import com.invages.orchidrus.slides.SlidesScreen
import com.invages.orchidrus.util.Utils

class SplashScreen : AppCompatActivity() {

    private val splashDelay = 2L;
    lateinit var firstTimeCheck: SharedPreferences
    val PREFS_NAME = "FirstCheck"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        firstTimeCheck = getSharedPreferences(PREFS_NAME, 0)

        Handler().postDelayed({
            kotlin.run {

                //      fortesting
                //Utils.setPreferenceValue(this, "token", "kjjhjhg")

/*
                val token: String? = Utils.getPreferenceValue(this, "token")

                if (token == "" || token == "null")
                    startActivity(Intent(this, LoginScreen::class.java))
                else
                    startActivity(Intent(this, HomeScreen::class.java))
*/


                if (firstTimeCheck.getString("firstTime", "") == "") {
                    startActivity(Intent(this@SplashScreen, SlidesScreen::class.java))
                } else {
                    startActivity(Intent(this@SplashScreen, LoginScreen::class.java))
                }

                finish()


            }
        }, splashDelay)
    }
}