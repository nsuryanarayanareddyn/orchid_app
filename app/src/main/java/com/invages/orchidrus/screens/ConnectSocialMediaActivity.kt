package com.invages.orchidrus.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.invages.orchidrus.R
import com.invages.orchidrus.adapter.SocialMediaItem
import com.invages.orchidrus.util.Utils
import kotlinx.android.synthetic.main.connect_social_media_activity.*

class ConnectSocialMediaActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.connect_social_media_activity)

        //Create RecyclerView
        recyclerView = findViewById(R.id.recyclerViewConnectToSocialMedia)

        val list = arrayListOf<SocialMediaItem>()
        list.add(SocialMediaItem(R.drawable.ic_facebook, "Facebook"))
        list.add(SocialMediaItem(R.drawable.ic_facebook, "Twitter"))
        list.add(SocialMediaItem(R.drawable.ic_facebook, "Youtube"))

        socialMediaSaveChanges.setOnClickListener {
            Utils.alertMessage(this, "Saved")
        }


    }
}