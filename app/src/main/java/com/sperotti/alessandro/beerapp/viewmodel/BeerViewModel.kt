package com.sperotti.alessandro.beerapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.sperotti.alessandro.beerapp.models.Beer
import com.sperotti.alessandro.beerapp.utils.BeerUtils
import java.util.*
import javax.inject.Inject

class BeerViewModel
@Inject constructor(val beersRepo: BeersRepo) : ViewModel() {

    lateinit var currentBeers : LiveData<PagedList<Beer>>

    init{
        currentBeers = beersRepo.beers
    }

    fun getBeers() : LiveData<PagedList<Beer>>{
        return currentBeers
    }

    fun getWithFilters(from : String?, to : String?, name : String?) {
        beersRepo.getWithFilters(
            BeerUtils.getDateFromDatePicker(from),
            BeerUtils.getDateFromDatePicker(to),
            BeerUtils.getFormattedBeerName(name)
            )
    }
}