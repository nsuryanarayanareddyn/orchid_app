package com.invages.orchidrus

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.intro_page.*

class IntroPage : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro_page)

        agree.setOnClickListener {
            startActivity(Intent(this, SignUpPage::class.java))
        }

        disagree.setOnClickListener {
            finish();
        }

    }
}
