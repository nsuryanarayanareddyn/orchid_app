package com.invages.orchidrus.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.icu.text.DateTimePatternGenerator.PatternInfo.OK
import android.preference.PreferenceManager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

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


        fun setDateYYYYMMDD(a: Activity?, tvName: TextView) {

            val myCalendar = Calendar.getInstance()
            val datePickerListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->

                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, month)
                myCalendar.set(Calendar.DAY_OF_MONTH, day)

                val sdf = SimpleDateFormat("HH:mm:ss")
                val formatedDate = sdf.format(myCalendar.time)

                var strDay = (day).toString()
                if (strDay.length == 1) {
                    strDay = "0$strDay"
                }
                var strMonth = (month + 1).toString()
                if (strMonth.length == 1) {
                    strMonth = "0$strMonth"
                }

                // repodatebtn.setText(strselectedDay+"/"+strselectedMonth+"/"+year);

                tvName.text = String.format("%s-%s-%s $formatedDate", year, strMonth, strDay)
                //tvName.text = String.format("%s/%s", strMonth, year)

            }
            val c = Calendar.getInstance()
            val mYear = c.get(Calendar.YEAR)
            val mMonth = c.get(Calendar.MONTH)
            val mDay = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(a!!, datePickerListener, mYear, mMonth, mDay)

            datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel") { dialog, which ->
                if (which == DialogInterface.BUTTON_NEGATIVE) {
                    dialog.cancel()
                }
            }
            datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK") { dialog, which ->
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    val datePicker = datePickerDialog.datePicker
                    datePickerListener.onDateSet(
                        datePicker,
                        datePicker.year,
                        datePicker.month,
                        datePicker.dayOfMonth
                    )

                }
            }
            datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
            datePickerDialog.setCancelable(false)
            datePickerDialog.show()
        }
    }

}