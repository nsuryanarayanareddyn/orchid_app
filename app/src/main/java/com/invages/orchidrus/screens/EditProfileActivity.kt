package com.invages.orchidrus.screens

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.invages.orchidrus.R
import com.invages.orchidrus.util.Utils
import com.invages.orchidrus.util.Utils.Companion.PLEASE_ENTER_DOB
import com.invages.orchidrus.util.Utils.Companion.PLEASE_ENTER_EMAIL
import com.invages.orchidrus.util.Utils.Companion.PLEASE_ENTER_NAME
import com.invages.orchidrus.util.Utils.Companion.PLEASE_ENTER_PHONE
import kotlinx.android.synthetic.main.create_event.*
import kotlinx.android.synthetic.main.edit_profile_activity.*
import kotlinx.android.synthetic.main.login_screen.*

class EditProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.edit_profile_activity)

        supportActionBar?.hide()

        btCloseEditProfile.setOnClickListener {
            finish()
        }

        btSubmitEditProfile.setOnClickListener {

            if (etNameEditProfile.text.toString() == "") {
                Utils.alertMessage(this, PLEASE_ENTER_NAME)
                return@setOnClickListener
            }

            if (etEmailProfileEdit.text.toString() == "") {
                Utils.alertMessage(this, PLEASE_ENTER_EMAIL)
                return@setOnClickListener
            }


            if (etPhoneProfileEdit.text.toString() == "") {
                Utils.alertMessage(this, PLEASE_ENTER_PHONE)
                return@setOnClickListener
            }

            if (etDobProfileEdit.text.toString() == "") {
                Utils.alertMessage(this, PLEASE_ENTER_DOB)
                return@setOnClickListener
            }


            finish()
        }

        dobEditProfile.setOnClickListener {
            Utils.setDateYYYYMMDD(this, etDobProfileEdit)
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
    }

}