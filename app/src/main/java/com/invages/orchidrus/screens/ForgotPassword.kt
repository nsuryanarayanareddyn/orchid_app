package com.invages.orchidrus.screens

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.invages.orchidrus.R
import kotlinx.android.synthetic.main.forgot_password.*
import kotlinx.android.synthetic.main.login_page.*

class ForgotPassword : AppCompatActivity() {

    val TAG = javaClass.simpleName

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
        passwordvisibility2.setOnClickListener {

            if (count % 2 == 0) {
                passwordvisibility2.setBackgroundResource(R.drawable.icon_visibility_on)
                etPasswordFPage.transformationMethod = PasswordTransformationMethod()
            }else {
                passwordvisibility2.setBackgroundResource(R.drawable.icon_visibility_off)
                etPasswordFPage.transformationMethod = null
            }

            count++
        }


        btnSendOTP.setOnClickListener {  }

        btnLoginFP.setOnClickListener {  }

    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
    }
}