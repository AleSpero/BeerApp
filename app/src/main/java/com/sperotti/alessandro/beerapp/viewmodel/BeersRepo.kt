package com.sperotti.alessandro.beerapp.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.sperotti.alessandro.beerapp.models.Beer
import com.sperotti.alessandro.beerapp.network.PunkEndpoint
import com.sperotti.alessandro.beerapp.utils.BeerDataSource
import com.sperotti.alessandro.beerapp.utils.BeerDataSourceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeersRepo @Inject constructor(val punkEndpoint: PunkEndpoint) {


    var beers : LiveData<PagedList<Beer>>
    var factory : BeerDataSourceFactory

    val pageConfig = PagedList.Config.Builder()
        .setPageSize(20)
        .setInitialLoadSizeHint(20)
        .build()

    init{
        factory = BeerDataSourceFactory(punkEndpoint)

        beers = LivePagedListBuilder<Int, Beer>(factory, pageConfig)
            .build()
    }

    fun getWithFilters(from : Date?, to : Date?, beerName : String?) {

        factory.from = from
        factory.to = to
        factory.beerName = beerName

        factory.instance.invalidate()

    }

}