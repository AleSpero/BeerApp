package com.sperotti.alessandro.beerapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView

import com.sperotti.alessandro.beerapp.R
import com.sperotti.alessandro.beerapp.viewmodel.BeerViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    lateinit var beerViewModel: BeerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_home, container, false)

        beerViewModel = ViewModelProviders.of(this).get(BeerViewModel::class.java)

        beerViewModel.getBeers().observe(this, Observer {
            //beerList.adapter = BeersAdapter(it)
        })

        return v
    }

}
