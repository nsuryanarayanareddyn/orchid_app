package com.invages.orchidrus.screens

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.invages.orchidrus.R
import com.invages.orchidrus.asynctask.WebAsyncTask
import com.invages.orchidrus.asynctask.WebResponseListener
import com.invages.orchidrus.retrofit.ApiClient
import com.invages.orchidrus.retrofit.ApiInterface
import com.invages.orchidrus.util.Utils
import com.invages.orchidrus.util.Utils.Companion.URL_LOGIN
import kotlinx.android.synthetic.main.login_screen.*
import org.json.JSONObject

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

            val mail = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (mail == "" && password == "") {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            } else if (mail == "") {
                Toast.makeText(this, "Please enter mail id", Toast.LENGTH_SHORT).show()
            } else if (password == "") {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
            } else {

                if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                    Toast.makeText(this, "Enter valid email id", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                /* val call: Call<LoginDetail> = apiInterface.login(LoginBody(mail, password))
                 call.enqueue(object : Callback<LoginDetail> {

                     override fun onResponse(call: Call<LoginDetail>?, response: Response<LoginDetail>?) {

                         val res: LoginDetail? = response?.body()

                         Log.i(TAG, "onResponse ${res?.status}")
                         Log.i(TAG, "onResponse ${res?.message}")
                         Log.i(TAG, "onResponse ${res?.status_code}")
                         Log.i(TAG, "onResponse ${res?.token}")

                         Utils.setPreferenceValue(this@LoginScreen, "token", "" + res?.token)
                         Utils.setPreferenceValue(this@LoginScreen, "user_id", "" + res?.userid)

                         if (res?.status == "Success") {
                             Toast.makeText(this@LoginScreen, res.message, Toast.LENGTH_SHORT).show()
                             startActivity(Intent(this@LoginScreen, HomeScreen::class.java))
                             finish()
                         }

                     }

                     override fun onFailure(call: Call<LoginDetail>?, t: Throwable?) {
                         Log.i(TAG, "onFailure ${t?.localizedMessage}")
                     }

                 })
 */

                val jObj = JSONObject()
                jObj.put("email", mail)
                jObj.put("password", password)

                WebAsyncTask(this, URL_LOGIN, jObj, object : WebResponseListener {

                    override fun onResponse(str: String?) {

                        Log.i(TAG, str)
                        val jObj = JSONObject(str)

                        if (jObj.getString("status") == "Success") {
                            Utils.setPreferenceValue(this@LoginScreen, "token", "" + jObj.getString("token"))
                            Utils.setPreferenceValue(this@LoginScreen, "user_id", "" + jObj.getString("user_id"))

                            Toast.makeText(this@LoginScreen, jObj.getString("message"), Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@LoginScreen, HomeScreen::class.java))
                            finish()

                        }


                    }

                    override fun onError(error: String?) {

                    }
                }).execute()

            }


        }

        forgot_password.setOnClickListener {
            startActivity(Intent(this, ForgotPassword::class.java))
        }


    }
}