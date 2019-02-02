package com.invages.orchidrus.util

import android.content.Context
import android.preference.PreferenceManager

class Utils {

    companion object {

        var SERVER_BASE_URL = "http://dameeko.com/orchid/orchid_v0.0.1/public"

        fun setPreferenceValue(context: Context, key: String, value: String) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putString(key, value)
            editor.apply()
        }

        fun getPreferenceValue(context: Context, key: String): String? {
            return PreferenceManager.getDefaultSharedPreferences(context).getString(key, "");
        }

    }

}