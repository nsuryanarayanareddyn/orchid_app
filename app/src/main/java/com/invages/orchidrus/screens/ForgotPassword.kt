package com.invages.orchidrus.screens

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.invages.orchidrus.R
import com.invages.orchidrus.retrofit.ApiClient
import com.invages.orchidrus.retrofit.ApiInterface
import com.invages.orchidrus.retrofit.model.LoginDetail
import kotlinx.android.synthetic.main.forgot_password.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPassword : AppCompatActivity() {

    val TAG = javaClass.simpleName
    lateinit var apiInterface: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )*/
        setContentView(R.layout.forgot_password)

        supportActionBar?.hide()

        apiInterface = ApiClient.getClient().create(ApiInterface::class.java)


        var count = 1
        passwordvisibility2.setOnClickListener {

            if (count % 2 == 0) {
                passwordvisibility2.setBackgroundResource(R.drawable.icon_visibility_on)
                etPasswordFPage.transformationMethod = PasswordTransformationMethod()
            } else {
                passwordvisibility2.setBackgroundResource(R.drawable.icon_visibility_off)
                etPasswordFPage.transformationMethod = null
            }

            count++
        }


        btnSendOTP.setOnClickListener {

            val email = etEmailFPPage.text.toString();

            if (email.isEmpty()) {
                Toast.makeText(this@ForgotPassword, "Enter email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast.makeText(this@ForgotPassword, "Enter valid email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val call: Call<LoginDetail> = apiInterface.sendOTP(email)

            call.enqueue(object : Callback<LoginDetail> {

                override fun onResponse(call: Call<LoginDetail>?, response: Response<LoginDetail>?) {

                    val res: LoginDetail? = response?.body()
                    Log.i(TAG, "onResponse $call")
                    Log.i(TAG, "onResponse ${res.toString()}")
                    Log.i(TAG, "onResponse ${res?.status}")
                    Log.i(TAG, "onResponse ${res?.message}")
                    Log.i(TAG, "onResponse ${res?.status_code}")
                    Log.i(TAG, "onResponse ${res?.token}")

                    Toast.makeText(this@ForgotPassword, "${res?.message}", Toast.LENGTH_SHORT).show()

                }

                override fun onFailure(call: Call<LoginDetail>?, t: Throwable?) {
                    Log.i(TAG, "${t?.message}")
                }

            })

        }


        btnLoginFP.setOnClickListener {

            val otp: Int = etOtp.text.toString().toInt();
            val email = etPasswordFPage.text.toString();


            /*if (otp.isEmpty()) {
                Toast.makeText(this@ForgotPassword, "Enter OTP", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }*/


            if (email.isEmpty()) {
                Toast.makeText(this@ForgotPassword, "Enter password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast.makeText(this@ForgotPassword, "Enter valid email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val call: Call<LoginDetail> = apiInterface.verifyOTP(otp, email)

            call.enqueue(object : Callback<LoginDetail> {

                override fun onResponse(call: Call<LoginDetail>?, response: Response<LoginDetail>?) {

                    val res: LoginDetail? = response?.body()
                    Log.i(TAG, "onResponse $call")
                    Log.i(TAG, "onResponse ${res.toString()}")
                    Log.i(TAG, "onResponse ${res?.status}")
                    Log.i(TAG, "onResponse ${res?.message}")
                    Log.i(TAG, "onResponse ${res?.status_code}")
                    Log.i(TAG, "onResponse ${res?.token}")

                    Toast.makeText(this@ForgotPassword, res?.message, Toast.LENGTH_SHORT).show()

                }

                override fun onFailure(call: Call<LoginDetail>?, t: Throwable?) {
                    Log.i(TAG, "onFailure $t")
                    Log.i(TAG, "onFailure ${t?.message}")
                }

            })

        }

    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
    }

}