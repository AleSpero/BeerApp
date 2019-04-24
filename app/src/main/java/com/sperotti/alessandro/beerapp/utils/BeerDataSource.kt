package com.sperotti.alessandro.beerapp.utils

import androidx.paging.PageKeyedDataSource
import com.sperotti.alessandro.beerapp.models.Beer
import com.sperotti.alessandro.beerapp.network.PunkEndpoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class BeerDataSource @Inject constructor(val punkEndpoint: PunkEndpoint, val from : Date?, val to : Date?) : PageKeyedDataSource<Int, Beer>() {

    val firstPage = 1
    val disposables = ArrayList<Disposable>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Beer>) {

        val single = if(from == null && to == null)
            punkEndpoint.getBeers(firstPage, params.requestedLoadSize)
            else
            punkEndpoint.getBeersBrewedInterval(
                BeerUtils.getFormattedDateForQuery(to),
                BeerUtils.getFormattedDateForQuery(from),
                firstPage, params.requestedLoadSize)


        disposables.add(
            single
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onResult(it, null, firstPage + 1)
            },
                {
                    it.printStackTrace()//TODO handle error
                })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Beer>) {

        val single = if(from == null && to == null)
            punkEndpoint.getBeers(firstPage, params.requestedLoadSize)
        else
            punkEndpoint.getBeersBrewedInterval(
                BeerUtils.getFormattedDateForQuery(from),
                BeerUtils.getFormattedDateForQuery(to),
                firstPage, params.requestedLoadSize)

        disposables.add(
            single
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onResult(it, params.key + 1)
            },
                {
                    it.printStackTrace()//TODO handle error
                })
        )    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Beer>) {
    }


}