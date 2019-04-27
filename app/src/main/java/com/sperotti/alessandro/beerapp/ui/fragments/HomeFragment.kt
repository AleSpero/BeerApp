package com.sperotti.alessandro.beerapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputLayout
import com.sperotti.alessandro.beerapp.R

import com.sperotti.alessandro.beerapp.di.components.DaggerAppComponent
import com.sperotti.alessandro.beerapp.di.modules.NetworkModule
import com.sperotti.alessandro.beerapp.models.Beer
import com.sperotti.alessandro.beerapp.ui.adapters.BeerAdapter
import com.sperotti.alessandro.beerapp.viewmodel.BeerViewModel
import com.sperotti.alessandro.beerapp.viewmodel.BeerViewModelFactory
import github.nisrulz.recyclerviewhelper.RVHItemClickListener
import github.nisrulz.recyclerviewhelper.RVHItemDividerDecoration
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class HomeFragment : Fragment() {

    @Inject
    lateinit var beerViewModelFactory: BeerViewModelFactory
    lateinit var beerViewModel: BeerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerAppComponent.builder()
            .networkModule(NetworkModule("https://api.punkapi.com/"))
            .build()
            .inject(this)

        beerViewModel = ViewModelProviders.of(this, beerViewModelFactory).get(BeerViewModel::class.java)

        savedInstanceState?.let {
            beerViewModel.currentlySelectedBeer?.let {
                showBeerDialog(it)
            }
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(com.sperotti.alessandro.beerapp.R.layout.fragment_home, container, false)

        val beerRecyclerView = v.findViewById<RecyclerView>(R.id.beerList)
        val searchBtn = v.findViewById<Button>(R.id.search_btn)

        val brewedAfter = v.findViewById<TextInputLayout>(R.id.fromDate)
        val brewedBefore = v.findViewById<TextInputLayout>(R.id.toDate)
        val beerName = v.findViewById<TextInputLayout>(R.id.beer_name)

        //recyclerview init
        beerRecyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = BeerAdapter()
        beerRecyclerView.adapter = adapter

        //Livedata triggerato - cambiamenti di page
        beerViewModel.getBeers().observe(this, Observer {
            //aggiornamento pagedlist
            adapter.submitList(it)
        })

        beerRecyclerView.addOnItemTouchListener(RVHItemClickListener(context,
            RVHItemClickListener.OnItemClickListener { view, position ->

                val beer = adapter.getItemAtPosition(position)
                beerViewModel.currentlySelectedBeer = beer
                showBeerDialog(beer)
            }
        ))

        searchBtn.setOnClickListener {
            beerViewModel.getWithFilters(
                brewedAfter.editText?.text.toString(),
                brewedBefore.editText?.text.toString(),
                beerName.editText?.text.toString())
        }



        return v
    }

    fun showBeerDialog(beer : Beer){

        val beerView = LayoutInflater.from(requireContext()).inflate(R.layout.beer_dialog, null)

        val title = beerView.findViewById<TextView>(R.id.title)
        val tagline = beerView.findViewById<TextView>(R.id.tagline)
        val image = beerView.findViewById<ImageView>(R.id.image)
        val abv = beerView.findViewById<TextView>(R.id.abv)
        val firstBrewed = beerView.findViewById<TextView>(R.id.first_brewed)

        val description = beerView.findViewById<TextView>(R.id.description)

        title?.text = beer.name
        tagline?.text = beer.tagline
        abv?.text = String.format("%s%%", beer.abv)
        firstBrewed?.text = beer.firstBrewed
        description?.text = beer.descr


        beer.imgUrl?.let {
            Glide.with(this)
                    .asDrawable()
                    .load(it)
                    .into(image!!)

        }

        val beerDialog =  AlertDialog.Builder(requireContext())
            .setView(beerView)
            .setNeutralButton(android.R.string.ok) { dialog, int ->
                beerViewModel.currentlySelectedBeer = null
                dialog.dismiss()
            }.create()

        beerDialog.show()

    }

}
