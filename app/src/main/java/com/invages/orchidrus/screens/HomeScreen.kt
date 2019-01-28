package com.invages.orchidrus.screens

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.invages.orchidrus.R
import com.invages.orchidrus.retrofit.ApiClient
import com.invages.orchidrus.retrofit.ApiInterface
import com.invages.orchidrus.retrofit.model.MultipleResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.annotation.NonNull
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.invages.orchidrus.fragment.AddEventFragment
import com.invages.orchidrus.fragment.HomeFragment
import com.invages.orchidrus.fragment.InviteFragment
import com.invages.orchidrus.fragment.ProfileFragment


class HomeScreen : AppCompatActivity() {

    lateinit var apiInterface: ApiInterface

    val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.invages.orchidrus.R.layout.home_activity)

        apiInterface = ApiClient.getClient().create(ApiInterface::class.java)

        retrofitCall()


        val navigation = this.findViewById<BottomNavigationView>(com.invages.orchidrus.R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {

        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            val fragment: Fragment
            when (item.itemId) {
                com.invages.orchidrus.R.id.navigation_shop -> {
                    // toolbar.setTitle("Shop")
                    fragment = HomeFragment()
                    loadFragment(fragment)
                    return true
                }
                com.invages.orchidrus.R.id.navigation_gifts -> {
                    //toolbar.setTitle("My Gifts")
                    fragment = InviteFragment()
                    loadFragment(fragment)
                    return true
                }
                com.invages.orchidrus.R.id.navigation_cart -> {
//                    toolbar.setTitle("Cart")
                    fragment = AddEventFragment()    
                    loadFragment(fragment)

                    return true
                }
                com.invages.orchidrus.R.id.navigation_profile -> {
//                    toolbar.setTitle("Profile")
                    fragment = ProfileFragment()
                    loadFragment(fragment)
                    return true
                }
            }
            return false
        }

    }

    private fun loadFragment(fragment: Fragment) {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
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