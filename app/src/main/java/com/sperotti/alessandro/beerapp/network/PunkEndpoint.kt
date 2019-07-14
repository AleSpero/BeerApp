package com.sperotti.alessandro.beerapp.network

import com.sperotti.alessandro.beerapp.models.Beer
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PunkEndpoint {

    @GET("/v2/beers")
    fun getBeersBrewedInterval(
        @Query("brewed_before") before: String?,
        @Query("brewed_after") after: String?,
        @Query("beer_name") beerName: String?,
        @Query("page") page : Int,
        @Query("per_page") pageSize : Int
    ): Single<List<Beer>>
}