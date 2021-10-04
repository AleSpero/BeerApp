package com.sperotti.alessandro.beerapp.network

import com.sperotti.alessandro.beerapp.models.Beer
import retrofit2.http.GET
import retrofit2.http.Query

interface PunkEndpoint {

    @GET("/v2/beers")
    suspend fun getBeersBrewedInterval(
        @Query("beer_name") beerName: String?,
        @Query("page") page : Int,
        @Query("per_page") pageSize : Int
    ): List<Beer>
}