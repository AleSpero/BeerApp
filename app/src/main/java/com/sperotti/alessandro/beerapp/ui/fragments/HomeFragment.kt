package com.sperotti.alessandro.beerapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sperotti.alessandro.beerapp.R

import com.sperotti.alessandro.beerapp.di.components.DaggerAppComponent
import com.sperotti.alessandro.beerapp.di.modules.NetworkModule
import com.sperotti.alessandro.beerapp.ui.adapters.BeerAdapter
import com.sperotti.alessandro.beerapp.viewmodel.BeerViewModel
import com.sperotti.alessandro.beerapp.viewmodel.BeerViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class HomeFragment : Fragment() {

    @Inject
    lateinit var beerViewModelFactory: BeerViewModelFactory
    lateinit var beerViewModel: BeerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO sposta?
        DaggerAppComponent.builder()
                .networkModule(NetworkModule("https://api.punkapi.com/"))
                .build()
                .inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(com.sperotti.alessandro.beerapp.R.layout.fragment_home, container, false)

        val beerRecyclerView = v.findViewById<RecyclerView>(R.id.beerList)
        val swipeToRefresh = v.findViewById<SwipeRefreshLayout>(R.id.swiperefresh)

        beerViewModel = ViewModelProviders.of(this, beerViewModelFactory).get(BeerViewModel::class.java)
        //recyclerview init
        beerRecyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = BeerAdapter()
        beerRecyclerView.adapter = adapter

        //Livedata triggerato - cambiamenti di page
        beerViewModel.getBeers().observe(this, Observer {

            //aggiornamento pagedlist
            adapter.submitList(it)

            if (swipeToRefresh.isRefreshing) swipeToRefresh.isRefreshing = false
        })

        swipeToRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)

        swipeToRefresh.setOnRefreshListener {
            beerViewModel.getBeers()
        }

        return v
    }

}
