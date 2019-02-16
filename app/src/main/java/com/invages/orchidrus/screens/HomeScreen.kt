package com.invages.orchidrus.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.invages.orchidrus.R
import com.invages.orchidrus.fragment.HomeFragment
import com.invages.orchidrus.fragment.InviteFragment
import com.invages.orchidrus.fragment.ProfileFragment
import com.invages.orchidrus.retrofit.ApiClient
import com.invages.orchidrus.retrofit.ApiInterface
import com.invages.orchidrus.retrofit.model.MultipleResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeScreen : AppCompatActivity() {

    lateinit var apiInterface: ApiInterface

    val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.home_activity)

        supportActionBar?.hide()

        apiInterface = ApiClient.getClient().create(ApiInterface::class.java)

//retrofitCall()


        val navigation = this.findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {

        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            val fragment: Fragment
            when (item.itemId) {
                R.id.home -> {
                    // toolbar.setTitle("Shop")
                    fragment = HomeFragment()
                    loadFragment(fragment)
                    return true
                }
                R.id.invite_people -> {
                    //toolbar.setTitle("My Gifts")
                    fragment = InviteFragment()
                    loadFragment(fragment)
                    return true
                }
                R.id.navigation_plus -> {
//                    toolbar.setTitle("Cart")
//                    fragment = AddEventFragment()
//                    loadFragment(fragment)
                    startActivity(Intent(this@HomeScreen, CreateEventActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                    return true
                }
                R.id.profile -> {
//                    toolbar.setTitle("Profile")
                    fragment = ProfileFragment()
                    loadFragment(fragment)
                    return true
                }
                R.id.logout -> {
//                    fragment = ProfileFragment()
//                    loadFragment(fragment)
//                    Utils.setPreferenceValue(this@HomeScreen, "token", "")
//                    finish()
                    startActivity(Intent(this@HomeScreen, EditProfileActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                    return true
                }
            }
            return false
        }

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null);
        transaction.commit()
    }

    fun retrofitCall() {

//URL--> https://reqres.in/api/unknown
//Response --> {"page":1,"per_page":3,"total":12,"total_pages":4,"data":[{"id":1,"name":"cerulean","year":2000,"color":"#98B2D1","pantone_value":"15-4020"},{"id":2,"name":"fuchsia rose","year":2001,"color":"#C74375","pantone_value":"17-2031"},{"id":3,"name":"true red","year":2002,"color":"#BF1932","pantone_value":"19-1664"}]}
//Call Retrofit APIS
        val callOne: Call<MultipleResource> = apiInterface.listResources
        callOne.enqueue(object : Callback<MultipleResource> {

            override fun onResponse(call: Call<MultipleResource>?, response: Response<MultipleResource>?) {
                Log.i(TAG, "" + call)
                Log.i(TAG, "onResponse " + response.toString())
                val resource = response?.body()
                Log.i(TAG, "onResponse ${resource.toString()}")
            }

            override fun onFailure(call: Call<MultipleResource>?, t: Throwable?) {
                Log.i(TAG, "onFailure")
            }
        })
    }

}