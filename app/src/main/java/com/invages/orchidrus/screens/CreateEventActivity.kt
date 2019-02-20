package com.invages.orchidrus.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.invages.orchidrus.R
import com.invages.orchidrus.adapter.CardsListAdapter
import com.invages.orchidrus.adapter.EventsListAdapter
import com.invages.orchidrus.asynctask.WebAsyncTask
import com.invages.orchidrus.asynctask.WebResponseListener
import com.invages.orchidrus.model.CardItem
import com.invages.orchidrus.model.PersonEvent
import com.invages.orchidrus.retrofit.ApiClient
import com.invages.orchidrus.retrofit.ApiInterface
import com.invages.orchidrus.retrofit.model.LoginDetail
import com.invages.orchidrus.util.Utils
import com.invages.orchidrus.util.Utils.Companion.URL_CREATE_EVENT
import kotlinx.android.synthetic.main.create_event.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class CreateEventActivity : AppCompatActivity() {


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
        inviteFriendsLayout.setOnClickListener { startActivity(Intent(this, InviteFriendsScreen::class.java)) }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewBirthdayCards)

        val list = arrayListOf<CardItem>()

        list.add(CardItem(R.drawable.happy_birthday_all_my_butt))
        list.add(CardItem(R.drawable.original_penguins_personalised_anniversary_card))
        list.add(CardItem(R.drawable.happy_birthday_all_my_butt))
        list.add(CardItem(R.drawable.original_penguins_personalised_anniversary_card))
        list.add(CardItem(R.drawable.original_penguins_personalised_anniversary_card))
        list.add(CardItem(R.drawable.original_penguins_personalised_anniversary_card))
        list.add(CardItem(R.drawable.original_penguins_personalised_anniversary_card))

        val mAdapter = CardsListAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, true)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = mAdapter

    }

    private fun saveEvent() {

        strEventName = etEventName.text.toString()
        strEventType = etEventOccasionType.text.toString()
        strEventDesc = etEventDesc.text.toString()
        strEventStartTime = etEventStartDate.text.toString()
        strEventResponseTime = etResponseDate.text.toString()
        strCreatedBy = "" + Utils.getPreferenceValue(baseContext, "user_id")

        var jObj = JSONObject();
        jObj.put("event_name", strEventName)
        jObj.put("event_description", strEventDesc)
        jObj.put("event_type_id", strEventType)
        jObj.put("event_start_time", strEventStartTime)
        jObj.put("event_response_by_time", strEventResponseTime)
        jObj.put("event_created_by", strCreatedBy)

        WebAsyncTask(this, URL_CREATE_EVENT, jObj, object : WebResponseListener {

            override fun onResponse(str: String?) {

                Log.i(TAG, "onResponse called $str")

//                {"status_code":200,"status":"Success","message":"Event Created Successfully","event_id":46}
                val jObj = JSONObject(str)

                if(jObj.getString("status") == "Success")
                    finish()


            }

            override fun onError(error: String?) {

            }
        }).execute()

        /*val call = apiInterface.createEvent(
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

        })*/
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
    }
}