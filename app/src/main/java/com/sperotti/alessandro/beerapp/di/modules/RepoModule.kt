package com.sperotti.alessandro.beerapp.di.modules

import com.sperotti.alessandro.beerapp.network.PunkEndpoint
import com.sperotti.alessandro.beerapp.viewmodel.BeerViewModelFactory
import com.sperotti.alessandro.beerapp.viewmodel.BeersRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepoModule {

    @Provides
    @Singleton
    fun provideRepo(punkEndpoint : PunkEndpoint) : BeersRepo {
        return BeersRepo(punkEndpoint)
    }

    @Provides
    @Singleton
    fun provideViewModelFactory(repo : BeersRepo) : BeerViewModelFactory {
        return BeerViewModelFactory(repo)
    }

}