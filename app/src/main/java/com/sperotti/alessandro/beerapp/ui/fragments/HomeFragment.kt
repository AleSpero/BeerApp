package com.sperotti.alessandro.beerapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout.END_ICON_CLEAR_TEXT
import com.sperotti.alessandro.beerapp.R
import com.sperotti.alessandro.beerapp.models.Beer
import com.sperotti.alessandro.beerapp.ui.adapters.BeerAdapter
import com.sperotti.alessandro.beerapp.utils.LoadingState
import com.sperotti.alessandro.beerapp.utils.RecyclerViewPaginator
import com.sperotti.alessandro.beerapp.viewmodel.BeerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {

    val viewModel: BeerViewModel by viewModels()
    val beerAdapter: BeerAdapter by lazy {
        BeerAdapter(mutableListOf()) {
            showBeerDialog(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState?.let {
            viewModel.currentlySelectedBeer?.let {
                showBeerDialog(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeLoadingState()

        beer_name.endIconMode = END_ICON_CLEAR_TEXT
        beer_name.setEndIconOnClickListener {
            lifecycleScope.launch {
                viewModel.loadingState.emit(LoadingState.Loading())
            }
            beer_name.editText?.text?.clear()
            fetchBeers(
                page = 1,
                beerQuery = null,
                needsClear = true
            )
        }

        fetchBeers(
            page = 1,
            beerQuery = beer_name.editText?.text?.toString()?.takeIf { it.isNotEmpty() })

        search_btn.setOnClickListener {
            lifecycleScope.launch {
                viewModel.loadingState.emit(LoadingState.Loading())
            }
            fetchBeers(
                page = 1,
                beerQuery = beer_name.editText?.text?.toString()?.takeIf { it.isNotEmpty() },
                needsClear = true
            )
        }
    }

    fun setupRecyclerView() {
        beerList.layoutManager = LinearLayoutManager(context)

        beerList.addOnScrollListener(object :
            RecyclerViewPaginator(beerList.layoutManager as LinearLayoutManager) {
            override fun loadMore(page: Int, length: Int) {
                fetchBeers(
                    page = page,
                    pageSize = length,
                    beerQuery = beer_name.editText?.text?.toString()?.takeIf { it.isNotEmpty() })
            }
        })

        beerList.adapter = beerAdapter
    }

    fun fetchBeers(
        page: Int,
        pageSize: Int = RecyclerViewPaginator.PAGE_SIZE,
        beerQuery: String?,
        needsClear: Boolean = false
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getBeers(page, pageSize, beerQuery)
                .collect { fetchedBeers ->
                    if (fetchedBeers.isEmpty() && beerAdapter.beers.isEmpty()) {
                        viewModel.loadingState.emit(LoadingState.Error(null))
                    } else {
                        viewModel.loadingState.emit(LoadingState.Idle())
                        if (needsClear)
                            beerAdapter.clear()
                        beerAdapter.addItems(fetchedBeers)
                    }
                }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("search", beer_name.editText?.text.toString())
        super.onSaveInstanceState(outState)
    }

    fun showBeerDialog(beer: Beer) {

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

        val beerDialog = MaterialAlertDialogBuilder(requireContext())
            .setView(beerView)
            .setNeutralButton(android.R.string.ok) { dialog, _ ->
                viewModel.currentlySelectedBeer = null
                dialog.dismiss()
            }.create()

        beerDialog.show()

    }

    fun observeLoadingState() {
        lifecycleScope.launch(context = Dispatchers.Main) {
            viewModel.loadingState.collectLatest {
                beerList.isVisible = it is LoadingState.Idle
                loading_overlay.isVisible = it is LoadingState.Loading
                error_overlay.isVisible = it is LoadingState.Error
            }
        }

    }

}
