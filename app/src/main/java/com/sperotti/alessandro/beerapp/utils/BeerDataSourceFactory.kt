package com.sperotti.alessandro.beerapp.utils

import androidx.paging.DataSource
import com.sperotti.alessandro.beerapp.models.Beer
import com.sperotti.alessandro.beerapp.network.PunkEndpoint

class BeerDataSourceFactory(val endpoint : PunkEndpoint) : DataSource.Factory<Int, Beer>() {

    override fun create(): DataSource<Int, Beer> {
        return BeerDataSource(endpoint)
    }
}