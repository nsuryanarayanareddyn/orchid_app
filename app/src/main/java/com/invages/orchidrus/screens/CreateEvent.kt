package com.invages.orchidrus.screens

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.invages.orchidrus.R
import com.invages.orchidrus.util.Utils
import kotlinx.android.synthetic.main.create_event.*
import okhttp3.internal.Util
import org.json.JSONObject
import java.lang.Exception

class CreateEvent : AppCompatActivity() {


    var strEventName = ""
    var strEventDesc = ""
    var strEventType = ""
    var strStartTime = ""
    var strResponseTime = ""
    var strCreatedBy = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.create_event)

        supportActionBar?.hide()

        closeButton.setOnClickListener { finish() }
        leftArrow.setOnClickListener { saveEvent() }
        dateCalendar.setOnClickListener { Utils.setDateYYYYMMDD(this, eventDate) }
        dateCalendarTwo.setOnClickListener { Utils.setDateYYYYMMDD(this, responseDate) }
    }

    private fun saveEvent() {


        try {
            val jObj = JSONObject()
            jObj.put("event_name", strEventName)
            jObj.put("event_description", strEventDesc)
            jObj.put("event_type_id", strEventType)
            jObj.put("event_start_time", strStartTime)
            jObj.put("event_response_by_time", strResponseTime)
            jObj.put("event_created_by", strCreatedBy)
        } catch (e: Exception) {

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
    }
}