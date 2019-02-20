package com.invages.orchidrus.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.invages.orchidrus.R
import com.invages.orchidrus.adapter.EventsListAdapter
import com.invages.orchidrus.asynctask.WebAsyncTask
import com.invages.orchidrus.asynctask.WebResponseListener
import com.invages.orchidrus.model.PersonEvent
import com.invages.orchidrus.util.Utils
import com.invages.orchidrus.util.Utils.Companion.URL_FUTURE_EVENTS
import com.invages.orchidrus.util.Utils.Companion.URL_PAST_EVENTS
import org.json.JSONArray
import org.json.JSONObject

class UpComingEventsFragment : Fragment() {

    var TAG = javaClass.simpleName;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.upcoming_events_fragment, container, false)

        val recyclerView = view.findViewById(R.id.recyclerViewEvents) as RecyclerView

        val list = arrayListOf<PersonEvent>()

        list.add(PersonEvent("Aditya Birthday", "2/10 people are attending birthday", "Birthday"))
        list.add(PersonEvent("Aditya Birthday", "2/10 people are attending birthday", "Birthday"))
        list.add(PersonEvent("Aditya Birthday", "2/10 people are attending birthday", "Birthday"))
        list.add(PersonEvent("Aditya Birthday", "2/10 people are attending birthday", "Birthday"))
        list.add(PersonEvent("Aditya Birthday", "2/10 people are attending birthday", "Birthday"))
        list.add(PersonEvent("Aditya Birthday", "2/10 people are attending birthday", "Birthday"))
        list.add(PersonEvent("Aditya Birthday", "2/10 people are attending birthday", "Birthday"))

        val jObj = JSONObject();
        jObj.put("user_id", Utils.getPreferenceValue(this.context, "user_id"))

        WebAsyncTask(context, URL_FUTURE_EVENTS, jObj, object : WebResponseListener {
            override fun onResponse(json: JSONObject?) {
                //{"status_code":200,"status":"Success","message":"Event Type List Retrieved Successfully","output":[{"event_type_id":"1","event_type_name":"OTHER","event_type_description":"","created_at":"2019-01-15 06:41:31","updated_at":"2019-01-15 06:41:31"},{"event_type_id":"2","event_type_name":"BIRTHDAY","event_type_description":"","created_at":"2019-01-15 06:41:31","updated_at":"2019-01-15 06:41:31"},{"event_type_id":"3","event_type_name":"ANNIVERSARY","event_type_description":"","created_at":"2019-01-15 06:41:31","updated_at":"2019-01-15 06:41:31"},{"event_type_id":"4","event_type_name":"CONDOLENSE","event_type_description":"","created_at":"2019-01-15 06:41:31","updated_at":"2019-01-15 06:41:31"},{"event_type_id":"5","event_type_name":"WEDDING","event_type_description":"","created_at":"2019-01-15 06:41:31","updated_at":"2019-01-15 06:41:31"},{"event_type_id":"6","event_type_name":"CONGRATULATIONS","event_type_description":"","created_at":"2019-01-15 06:41:31","updated_at":"2019-01-15 06:41:31"},{"event_type_id":"7","event_type_name":"GET WELL","event_type_description":"","created_at":"2019-01-15 06:41:31","updated_at":"2019-01-15 06:41:31"},{"event_type_id":"8","event_type_name":"THANK YOU","event_type_description":"","created_at":"2019-01-15 06:41:31","updated_at":"2019-01-15 06:41:31"},{"event_type_id":"9","event_type_name":"RETIREMENT","event_type_description":"","created_at":"2019-01-15 06:41:31","updated_at":"2019-01-15 06:41:31"},{"event_type_id":"10","event_type_name":"NEW BABY","event_type_description":"","created_at":"2019-01-15 06:41:31","updated_at":"2019-01-15 06:41:31"},{"event_type_id":"11","event_type_name":"FAREWELL","event_type_description":"","created_at":"2019-01-15 06:41:31","updated_at":"2019-01-15 06:41:31"},{"event_type_id":"12","event_type_name":"GRADUATION","event_type_description":"","created_at":"2019-01-15 06:41:31","updated_at":"2019-01-15 06:41:31"}]}

                Log.i(TAG, "onResponse ${json.toString()}")

                if (json?.getString("status") == "Success") {

                    val array = JSONArray(json.getString("output"))
//                    for(obj in array)
                }

            }

            override fun onError(error: String?) {
                Log.i(TAG, "onError")
            }
        }).execute();

        val mAdapter = EventsListAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = mAdapter

        return view
    }

}