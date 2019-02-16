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
import com.invages.orchidrus.model.PersonEvent

class PastEventsFragment : Fragment() {

    var TAG = javaClass.simpleName;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.past_events_fragment, container, false)

        val recyclerView = view.findViewById(R.id.recyclerViewEvents) as RecyclerView

        val list = arrayListOf<PersonEvent>()

        list.add(PersonEvent("Aditya Birthday", "2/10 people are attending birthday", "Birthday"))
        list.add(PersonEvent("Aditya Birthday", "2/10 people are attending birthday", "Birthday"))
        list.add(PersonEvent("Aditya Birthday", "2/10 people are attending birthday", "Birthday"))
        list.add(PersonEvent("Aditya Birthday", "2/10 people are attending birthday", "Birthday"))
        list.add(PersonEvent("Aditya Birthday", "2/10 people are attending birthday", "Birthday"))
        list.add(PersonEvent("Aditya Birthday", "2/10 people are attending birthday", "Birthday"))
        list.add(PersonEvent("Aditya Birthday", "2/10 people are attending birthday", "Birthday"))

        val mAdapter = EventsListAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = mAdapter

        return view
    }
}