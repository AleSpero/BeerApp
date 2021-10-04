package com.sperotti.alessandro.beerapp.utils

import java.util.*

object BeerUtils {

    fun getFormattedBeerName(name: String?): String? {
        return if (name?.isEmpty() != false) null
        else name.lowercase(Locale.getDefault()).replace(" ", "_")
    }

}