package com.invages.orchidrus.screens

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.invages.orchidrus.R
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
        setContentView(R.layout.home_activity)

        apiInterface = ApiClient.getClient().create(ApiInterface::class.java)


        //Call Retrofit APIS
        val callOne: Call<MultipleResource> = apiInterface.listResources
        callOne.enqueue(object : Callback<MultipleResource> {

            override fun onResponse(call: Call<MultipleResource>?, response: Response<MultipleResource>?) {
                Log.i(TAG, ""+call)
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