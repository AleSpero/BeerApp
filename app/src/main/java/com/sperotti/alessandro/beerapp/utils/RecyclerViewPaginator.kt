package com.sperotti.alessandro.beerapp.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE

abstract class RecyclerViewPaginator(val layoutManager : LinearLayoutManager) : RecyclerView.OnScrollListener() {

    companion object{
        const val PAGE_SIZE = 20
    }

    var currentPage : Int = 1
    var prefetchDistance = 10

    val startSize: Int
        get() = ++currentPage

    val maxSize: Int
        get() = currentPage + PAGE_SIZE


    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == SCROLL_STATE_IDLE) {
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val firstVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

            if (visibleItemCount + firstVisibleItemPosition + prefetchDistance >= totalItemCount) {
                loadMore(startSize, maxSize)
            }
        }
    }

    abstract fun loadMore(page : Int, length : Int)


}