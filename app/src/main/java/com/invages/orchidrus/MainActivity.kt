package com.invages.orchidrus

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import com.facebook.*
import com.facebook.Profile.getCurrentProfile
import com.facebook.login.LoginResult
import com.facebook.appevents.internal.ActivityLifecycleTracker.startTracking
import com.facebook.ProfileTracker
import com.facebook.AccessToken
import com.facebook.AccessTokenTracker
import com.facebook.CallbackManager
import com.facebook.FacebookSdk
import com.facebook.FacebookException
import android.widget.Toast
import com.facebook.Profile.getCurrentProfile
import com.facebook.FacebookCallback
import com.facebook.login.widget.LoginButton


class MainActivity : AppCompatActivity() {

    var callbackManager: CallbackManager? = null
    lateinit var accessTokenTracker: AccessTokenTracker
    lateinit var profileTracker: ProfileTracker

    var TAG = "MainActivity";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FacebookSdk.sdkInitialize(applicationContext)
        setContentView(R.layout.activity_main)

        callbackManager = CallbackManager.Factory.create()
        accessTokenTracker = object : AccessTokenTracker() {
            override fun onCurrentAccessTokenChanged(oldToken: AccessToken, newToken: AccessToken) {
                Log.i(TAG, "onCurrentAccessTokenChanged")
            }
        }

        profileTracker = object : ProfileTracker() {
            override fun onCurrentProfileChanged(oldProfile: Profile, newProfile: Profile) {
                Log.i(TAG, "onCurrentAccessTokenChanged")
                nextActivity(newProfile)
            }
        }
        accessTokenTracker.startTracking()
        profileTracker.startTracking()


        val loginButton = findViewById<LoginButton>(R.id.login_button)
//        callback = object : FacebookCallback<LoginResult> {
//            override fun onSuccess(loginResult: LoginResult) {
//                val accessToken = loginResult.accessToken
//                val profile = Profile.getCurrentProfile()
//                nextActivity(profile)
//                Toast.makeText(applicationContext, "Logging in...", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onCancel() {
//                Log.i(TAG, "onCancel ")
//            }
//            override fun onError(e: FacebookException) {
//                Log.i(TAG, "onError "+e.message)
//            }
//        }
        loginButton.setReadPermissions("email")
//        loginButton.setReadPermissions("user_friends")
//        loginButton.setReadPermissions("user_posts")
//        loginButton.setReadPermissions("user_tagged_places")
//        loginButton.setReadPermissions("user_videos")
        loginButton.registerCallback(callbackManager, callback)

    }

    private fun nextActivity(profile: Profile?) {

        startActivity(Intent(this, HomeActivity::class.java))
    }

    private var callback = object : FacebookCallback<LoginResult> {
        override fun onSuccess(loginResult: LoginResult) {
            Log.i(TAG, "onSuccess")
            val profile = Profile.getCurrentProfile()
            nextActivity(profile)
        }

        override fun onCancel() {
            Log.i(TAG, "onCancel ")
        }

        override fun onError(e: FacebookException) {
            Log.i(TAG, "onError "+e.message)
        }
    }

}
