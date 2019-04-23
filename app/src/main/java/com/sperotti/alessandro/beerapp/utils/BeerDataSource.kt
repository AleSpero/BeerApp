package com.sperotti.alessandro.beerapp.utils

import androidx.paging.PageKeyedDataSource
import com.sperotti.alessandro.beerapp.models.Beer

class BeerDataSource : PageKeyedDataSource<String, Beer>() {

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Beer>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Beer>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Beer>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}