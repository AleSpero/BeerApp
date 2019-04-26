package com.sperotti.alessandro.beerapp.utils

import androidx.paging.DataSource
import com.sperotti.alessandro.beerapp.models.Beer
import com.sperotti.alessandro.beerapp.network.PunkEndpoint
import java.util.*

class BeerDataSourceFactory(val endpoint : PunkEndpoint) : DataSource.Factory<Int, Beer>() {

   lateinit var instance : BeerDataSource


    var beerName : String? = null
    var from : Date? = null
    var to : Date? = null

    override fun create(): DataSource<Int, Beer> {
        instance = BeerDataSource(endpoint, from, to, beerName)
        return instance
    }


}