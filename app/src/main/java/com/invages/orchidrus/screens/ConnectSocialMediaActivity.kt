package com.invages.orchidrus.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.invages.orchidrus.R

class ConnectSocialMediaActivity:AppCompatActivity() {

    lateinit var recyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.connect_social_media_activity)

        //Create RecyclerView
        recyclerView = findViewById(R.id.recyclerViewConnectToSocialMedia)

    }
}