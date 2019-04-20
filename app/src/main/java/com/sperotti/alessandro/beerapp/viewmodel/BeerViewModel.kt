package com.sperotti.alessandro.beerapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sperotti.alessandro.beerapp.models.Beer
import javax.inject.Inject

class BeerViewModel(@Inject public var beersRepo: BeersRepo) : ViewModel() {

    lateinit var currentBeers : LiveData<List<Beer>>

    fun init(){
        currentBeers = beersRepo.getBeers(1)
    }

    fun getBeers() : LiveData<List<Beer>>{
        return currentBeers
    }

}