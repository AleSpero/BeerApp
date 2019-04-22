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

        val v = inflater.inflate(R.layout.fragment_home, container, false)

        val beerRecyclerView = v.findViewById<RecyclerView>(R.id.beerList)

        beerViewModel = ViewModelProviders.of(this, beerViewModelFactory).get(BeerViewModel::class.java)
        beerViewModel.init()

        //recyclerview init
        beerRecyclerView.layoutManager = LinearLayoutManager(context)

        beerViewModel.getBeers().observe(this, Observer {
            beerRecyclerView.adapter = BeerAdapter(it)
        })

        return v
    }

}
