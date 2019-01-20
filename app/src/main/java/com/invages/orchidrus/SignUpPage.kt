package com.invages.orchidrus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.signup_page.*

class SignUpPage : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_page_two)

        facebook_button.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}
