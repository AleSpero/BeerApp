package com.sperotti.alessandro.beerapp.utils

import androidx.paging.PageKeyedDataSource
import com.sperotti.alessandro.beerapp.models.Beer
import com.sperotti.alessandro.beerapp.network.PunkEndpoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BeerDataSource @Inject constructor(val punkEndpoint: PunkEndpoint) : PageKeyedDataSource<Int, Beer>() {

    val firstPage = 1
    val disposables = ArrayList<Disposable>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Beer>) {
        disposables.add(punkEndpoint.getBeers(firstPage, params.requestedLoadSize)
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
        disposables.add(punkEndpoint.getBeers(params.key, params.requestedLoadSize)
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