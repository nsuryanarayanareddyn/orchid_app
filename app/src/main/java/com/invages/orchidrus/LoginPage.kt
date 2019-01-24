package com.invages.orchidrus

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.login_page.*

class LoginPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )*/
        setContentView(R.layout.login_page)

        supportActionBar?.hide()


        passwordvisibility.setOnClickListener {

        }

        btLogin.setOnClickListener {

            val mail = etEmail.text.toString().trim()
            val pw = etPassword.text.toString().trim()

            if (mail == "test" && pw == "123") {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            }else if(mail == ""){
                Toast.makeText(this, "Please Enter mail id", Toast.LENGTH_SHORT).show()
            } else if(pw == ""){
                Toast.makeText(this, "Please Enter password", Toast.LENGTH_SHORT).show()
            }
        }

        forgot_password.setOnClickListener {
            startActivity(Intent(this, ForgotPassword::class.java))
        }


    }
}