package com.sperotti.alessandro.beerapp.utils

import android.text.TextUtils
import java.text.SimpleDateFormat
import java.util.*

class BeerUtils {

    companion object {

        val dateFormatQuery = SimpleDateFormat("MM-yyyy")
        val dateFormatPicker = SimpleDateFormat("dd/MM/yyyy")

        fun getFormattedDateForQuery(date : Date?) : String?{
            return if(date != null) dateFormatQuery.format(date)
                        else null
        }

        fun getDateFromDatePicker(dateString : String?) : Date?{
            return if(TextUtils.isEmpty(dateString)) null
                    else dateFormatPicker.parse(dateString)
        }

        fun getFormattedBeerName(name : String?) : String?{
            return if(TextUtils.isEmpty(name)) null
            else name?.toLowerCase()?.replace(" ", "_")
        }

    }

}