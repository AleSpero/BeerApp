package com.sperotti.alessandro.beerapp.network

import androidx.lifecycle.LiveData
import com.sperotti.alessandro.beerapp.models.Beer
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PunkEndpoint {

    //TODO calls
    @GET("/v2/beers")
    fun getBeers(@Query("page") page : Int, @Query("per_page") pageSize : Int): Single<List<Beer>>

    @GET("/v2/beers")
    fun getBeersBrewedInterval(
        @Query("brewed_before") before: String,
        @Query("brewed_after") after: String,
        @Query("page") page : Int
    ): Single<List<Beer>>
}