package com.invages.orchidrus.screens

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import androidx.appcompat.app.AppCompatActivity
import com.invages.orchidrus.R
import kotlinx.android.synthetic.main.login_page.*

class ForgotPassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )*/
        setContentView(R.layout.forgot_password)

        supportActionBar?.hide()


        var count = 1
        passwordvisibility.setOnClickListener {

            if (count % 2 == 0) {
                passwordvisibility.setBackgroundResource(R.drawable.icon_visibility_on)
                etPassword.transformationMethod = PasswordTransformationMethod()
            }else {
                passwordvisibility.setBackgroundResource(R.drawable.icon_visibility_off)
                etPassword.transformationMethod = null
            }

            count++
        }
    }
}