package com.sperotti.alessandro.beerapp.viewmodel

import com.sperotti.alessandro.beerapp.models.Beer
import com.sperotti.alessandro.beerapp.network.PunkEndpoint
import com.sperotti.alessandro.beerapp.utils.BeerUtils
import com.sperotti.alessandro.beerapp.utils.LoadingState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeersRepo @Inject constructor(val punkEndpoint: PunkEndpoint) {

    suspend fun getData(startingPage : Int, pageSize : Int,
                beerName : String?) : Flow<List<Beer>> {
        return flow {
            try {
                emit(
                    punkEndpoint.getBeersBrewedInterval(
                        beerName,
                        startingPage,
                        pageSize
                    )
                )
            } catch (e : Exception){
                println(e.localizedMessage)
                emit(emptyList<Beer>())
            }
        }
    }

}