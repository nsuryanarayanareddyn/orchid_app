package com.invages.orchidrus.screens

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.invages.orchidrus.R
import com.invages.orchidrus.retrofit.ApiClient
import com.invages.orchidrus.retrofit.ApiInterface
import com.invages.orchidrus.retrofit.model.LoginUser
import kotlinx.android.synthetic.main.login_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field

class LoginScreen : AppCompatActivity() {

    lateinit var apiInterface: ApiInterface

    var TAG = javaClass.simpleName;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )*/
        setContentView(R.layout.login_screen)

        supportActionBar?.hide()

        apiInterface = ApiClient.getClient().create(ApiInterface::class.java)


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

            var mail = etEmail.text.toString().trim()
            var pw = etPassword.text.toString().trim()

            mail = "abc@gmail.com"
            pw = "abc@123"

            val call: Call<LoginUser> = apiInterface.login(mail, pw)
            call.enqueue(object : Callback<LoginUser> {

                override fun onResponse(call: Call<LoginUser>?, response: Response<LoginUser>?) {
                    val resource = response?.body()
                    Log.i(TAG, "onResponse $call")
                    Log.i(TAG, "onResponse $response")
                    Log.i(TAG, "onResponse ${resource.toString()}")
                }

                override fun onFailure(call: Call<LoginUser>?, t: Throwable?) {
                    Log.i(TAG, "onFailure ${t?.localizedMessage}")
                }
            })


            /*if (mail == "test" && pw == "123") {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeScreen::class.java))
            } else if (mail == "") {
                Toast.makeText(this, "Please Enter mail id", Toast.LENGTH_SHORT).show()
            } else if (pw == "") {
                Toast.makeText(this, "Please Enter password", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Invalid username/password", Toast.LENGTH_SHORT).show()
            }*/
        }

        forgot_password.setOnClickListener {
            startActivity(Intent(this, ForgotPassword::class.java))
        }


    }
}