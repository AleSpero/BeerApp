package com.sperotti.alessandro.beerapp.utils

import androidx.paging.DataSource
import com.sperotti.alessandro.beerapp.models.Beer
import com.sperotti.alessandro.beerapp.network.PunkEndpoint
import java.util.*

class BeerDataSourceFactory(val endpoint : PunkEndpoint) : DataSource.Factory<Int, Beer>() {

    constructor(endpoint : PunkEndpoint, from : Date?, to : Date?) : this(endpoint) {
        this.from = from
        this.to = to
    }

   lateinit var instance : BeerDataSource

    var from : Date? = null
    var to : Date? = null

    override fun create(): DataSource<Int, Beer> {
        instance = BeerDataSource(endpoint, from, to)
        return instance
    }


}