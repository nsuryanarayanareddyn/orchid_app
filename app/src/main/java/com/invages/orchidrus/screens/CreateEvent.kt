package com.invages.orchidrus.screens

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.invages.orchidrus.R
import com.invages.orchidrus.retrofit.ApiClient
import com.invages.orchidrus.retrofit.ApiInterface
import com.invages.orchidrus.retrofit.model.LoginDetail
import com.invages.orchidrus.util.Utils
import kotlinx.android.synthetic.main.create_event.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class CreateEvent : AppCompatActivity() {


    var strEventName = ""
    var strEventDesc = ""
    var strEventType = ""
    var strEventStartTime = ""
    var strEventResponseTime = ""
    var strCreatedBy = ""

    var TAG = javaClass.simpleName
    lateinit var apiInterface: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.create_event)

        supportActionBar?.hide()

        apiInterface = ApiClient.getClient().create(ApiInterface::class.java)


        closeButton.setOnClickListener { finish() }
        leftArrow.setOnClickListener { saveEvent() }
        dateCalendar.setOnClickListener { Utils.setDateYYYYMMDD(this, etEventStartDate) }
        dateCalendarTwo.setOnClickListener { Utils.setDateYYYYMMDD(this, etResponseDate) }
    }

    private fun saveEvent() {

        strEventName = etEventName.text.toString()
        strEventType = etEventOccasionType.text.toString()
        strEventDesc = etEventDesc.text.toString()
        strEventStartTime = etEventStartDate.text.toString()
        strEventResponseTime = etResponseDate.text.toString()
        strCreatedBy = ""+Utils.getPreferenceValue(baseContext, "token")


        val call = apiInterface.createEvent(
            strEventName,
            strEventDesc,
            strEventType,
            strEventStartTime,
            strEventResponseTime,
            strCreatedBy
        )
        call.enqueue(object : Callback<LoginDetail> {

            override fun onResponse(call: Call<LoginDetail>?, response: Response<LoginDetail>?) {
                val res = response?.body()

                Log.i(TAG, "onResponse ${res?.status}")
                Log.i(TAG, "onResponse ${res?.message}")
                Log.i(TAG, "onResponse ${res?.status_code}")
            }

            override fun onFailure(call: Call<LoginDetail>?, t: Throwable?) {
                Log.i(TAG, "onFailure ")
            }

        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
    }
}