package com.sperotti.alessandro.beerapp

import com.sperotti.alessandro.beerapp.models.Beer
import com.sperotti.alessandro.beerapp.network.PunkEndpoint
import java.lang.IllegalArgumentException

class ErrorEndpoint : PunkEndpoint {
    override suspend fun getBeersBrewedInterval(
        beerName: String?,
        page: Int,
        pageSize: Int
    ): List<Beer> {
        throw IllegalArgumentException("This is the error message.")
    }
}