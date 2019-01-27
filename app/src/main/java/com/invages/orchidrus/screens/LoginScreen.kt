package com.invages.orchidrus.screens

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.invages.orchidrus.R
import kotlinx.android.synthetic.main.login_page.*

class LoginScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )*/
        setContentView(R.layout.login_page)

        supportActionBar?.hide()


        var count = 1
        passwordvisibility.setOnClickListener {

            if (count % 2 == 0) {
                passwordvisibility.setBackgroundResource(R.drawable.icon_visibility_on)
                etPassword.transformationMethod = PasswordTransformationMethod()
            } else {
                passwordvisibility.setBackgroundResource(R.drawable.icon_visibility_off)
                etPassword.transformationMethod = null
            }

            count++
        }

        btLogin.setOnClickListener {

            val mail = etEmail.text.toString().trim()
            val pw = etPassword.text.toString().trim()

            if (mail == "test" && pw == "123") {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeScreen::class.java))
            } else if (mail == "") {
                Toast.makeText(this, "Please Enter mail id", Toast.LENGTH_SHORT).show()
            } else if (pw == "") {
                Toast.makeText(this, "Please Enter password", Toast.LENGTH_SHORT).show()
            }
        }

        forgot_password.setOnClickListener {
            startActivity(Intent(this, ForgotPassword::class.java))
        }


    }
}