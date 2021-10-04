package com.sperotti.alessandro.beerapp

import com.sperotti.alessandro.beerapp.models.Beer
import com.sperotti.alessandro.beerapp.network.PunkEndpoint

class FakePunkEndpoint : PunkEndpoint {
    override suspend fun getBeersBrewedInterval(
        beerName: String?,
        page: Int,
        pageSize: Int
    ): List<Beer> = listOf(
        Beer("Beer 1", "Best beer ever!", "This is a fancy beer.", null, "1.0", "10/2021"),
        Beer("Beer 2", "Best beer ever!", "This is a fancy beer.", null, "1.0", "10/2021"),
        Beer("Beer 3", "Best beer ever!", "This is a fancy beer.", null, "1.0", "10/2021"),
        Beer("Beer 4", "Best beer ever!", "This is a fancy beer.", null, "1.0", "10/2021"),
        Beer("Beer 5", "Best beer ever!", "This is a fancy beer.", null, "1.0", "10/2021"),
        Beer("Beer 6", "Best beer ever!", "This is a fancy beer.", null, "1.0", "10/2021"),
        Beer("Beer 7", "Best beer ever!", "This is a fancy beer.", null, "1.0", "10/2021")
    )
}