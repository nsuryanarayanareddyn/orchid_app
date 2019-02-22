package com.invages.orchidrus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.invages.orchidrus.R
import com.invages.orchidrus.model.PersonEvent2

class EventsListAdapter(private var list: ArrayList<PersonEvent2>) :
    RecyclerView.Adapter<EventsListAdapter.MyViewAdapter>() {

    inner class MyViewAdapter(view: View) : RecyclerView.ViewHolder(view) {
        internal var birdayPic: ImageView = view.findViewById(R.id.birthday_pic) as ImageView
        internal var name: TextView = view.findViewById(R.id.name) as TextView
        internal var desc: TextView = view.findViewById(R.id.message) as TextView
        internal var eventType: TextView = view.findViewById(R.id.tvEventType) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewAdapter {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_row_item, parent, false)
        return MyViewAdapter(view)
    }

    override fun onBindViewHolder(holder: MyViewAdapter, position: Int) {
        val item = list[position]

        holder.name.text = item.name
        holder.desc.text = item.desc
        holder.eventType.text = item.eventTypeName
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
