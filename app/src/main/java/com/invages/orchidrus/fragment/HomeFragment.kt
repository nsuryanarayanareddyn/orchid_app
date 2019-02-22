package com.invages.orchidrus.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.invages.orchidrus.R
import com.invages.orchidrus.retrofit.ApiClient
import com.invages.orchidrus.retrofit.ApiInterface
import com.invages.orchidrus.retrofit.model.ListEvents
import com.invages.orchidrus.util.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    var TAG = javaClass.simpleName;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.home_fragment, container, false)


        val apiInterface = ApiClient.getClient().create(ApiInterface::class.java)

        val token = Utils.getPreferenceValue(activity!!.baseContext, "token")

        /*val cal = apiInterface.getListEventType(token + "")

        cal.enqueue(object : Callback<ListEvents> {

            override fun onResponse(call: Call<ListEvents>?, response: Response<ListEvents>?) {
                Log.i(TAG, "onResponse")
            }

            override fun onFailure(call: Call<ListEvents>?, t: Throwable?) {
                Log.i(TAG, "onFailure")
            }
        })
*/
        val viewPager: ViewPager = view.findViewById(R.id.viewpagerHome)
        val tabLayout: TabLayout = view.findViewById(R.id.result_tabs)

        setUpViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)




        return view
    }

    fun setUpViewPager(viewPager: ViewPager) {
        val eventsAdapter = EventFragmentsAdapter(childFragmentManager)
        eventsAdapter.addFragment(UpComingEventsFragment(), "Upcoming Events")
        eventsAdapter.addFragment(PastEventsFragment(), "Past Events")
        viewPager.adapter = eventsAdapter
    }
}