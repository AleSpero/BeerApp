package com.sperotti.alessandro.beerapp.viewmodel

import androidx.lifecycle.LiveData
import com.sperotti.alessandro.beerapp.models.Beer
import com.sperotti.alessandro.beerapp.network.PunkEndpoint
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeersRepo {

    lateinit var punkEndpoint : PunkEndpoint

    fun getBeers() : LiveData<List<Beer>>?{
        //TODO handle network call here
        return null //TODO remove ?
    }


}