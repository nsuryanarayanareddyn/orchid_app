package com.invages.orchidrus.slides

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.github.paolorotolo.appintro.AppIntro
import com.invages.orchidrus.screens.LoginScreen

class SlidesScreen : AppIntro() {

    val PREFS_NAME = "FirstCheck"
    internal var TAG = javaClass.simpleName

    override fun init(savedInstanceState: Bundle?) {
        /*requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )*/
        super.init(savedInstanceState)

        supportActionBar?.hide()

        val firstTimeCheck = this.getSharedPreferences(PREFS_NAME, 0)
        val editor = firstTimeCheck.edit()
        editor.putString("firstTime", "NO")
        editor.apply()

        val first = SlideOne()
        val sec = SlideTwo()
        val thr = SlideThree()

        this.addSlide(first)
        this.addSlide(sec)
        this.addSlide(thr)

    }

    override fun onSkipPressed() {
        super.onSkipPressed()
    }

    override fun onDonePressed() {
        super.onDonePressed()
        startActivity(Intent(this, LoginScreen::class.java))
        finish()
    }

}