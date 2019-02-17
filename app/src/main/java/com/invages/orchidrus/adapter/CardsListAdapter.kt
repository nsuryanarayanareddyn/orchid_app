package com.invages.orchidrus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.invages.orchidrus.R
import com.invages.orchidrus.model.CardItem

class CardsListAdapter(private var list: ArrayList<CardItem>) : RecyclerView.Adapter<CardsListAdapter.MyViewAdapter>() {

    var li = arrayListOf<CardItem>()

    init {
        this.li = list
    }

    inner class MyViewAdapter(view: View) : RecyclerView.ViewHolder(view) {
        internal var birdayPic: ImageView = view.findViewById(R.id.cardImage) as ImageView
//        internal var name: TextView = view.findViewById(R.id.name) as TextView
//        internal var desc: TextView = view.findViewById(R.id.message) as TextView
//        internal var eventType: TextView = view.findViewById(R.id.tvEventType) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewAdapter {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.birthday_card_item, parent, false)
        return MyViewAdapter(view)
    }

    override fun onBindViewHolder(holder: MyViewAdapter, position: Int) {
        val item = li[position]
        holder.birdayPic.setBackgroundResource(item.image)
//        holder.name.text = item.name
//        holder.desc.text = item.desc
//        holder.eventType.text = item.eventType
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
