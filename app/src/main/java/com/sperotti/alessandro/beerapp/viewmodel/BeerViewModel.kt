package com.sperotti.alessandro.beerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sperotti.alessandro.beerapp.models.Beer
import com.sperotti.alessandro.beerapp.utils.BeerUtils
import com.sperotti.alessandro.beerapp.utils.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerViewModel
@Inject constructor(val beersRepo: BeersRepo) : ViewModel() {

    var currentlySelectedBeer : Beer? = null
    var searchQuery : String? = null

    var loadingState : MutableStateFlow<LoadingState> = MutableStateFlow(LoadingState.Loading())

    suspend fun getBeers(page : Int, pageSize : Int, name : String?) : Flow<List<Beer>> {
        return beersRepo.getData(
                page, pageSize,
                BeerUtils.getFormattedBeerName(name)
            )
    }


}