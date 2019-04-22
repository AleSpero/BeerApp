package com.sperotti.alessandro.beerapp.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sperotti.alessandro.beerapp.models.Beer
import com.sperotti.alessandro.beerapp.network.PunkEndpoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeersRepo @Inject constructor(val punkEndpoint: PunkEndpoint) {

    fun getBeers(page : Int?) : LiveData<List<Beer>>{
        val beerData = MutableLiveData<List<Beer>>()

        punkEndpoint.getBeers(page ?: 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                beerData.value = it
            },
                {
                    it.printStackTrace()//TODO handle error
                })

        return beerData
    }


}