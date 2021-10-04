package com.sperotti.alessandro.beerapp.di.modules

import com.sperotti.alessandro.beerapp.network.PunkEndpoint
import com.sperotti.alessandro.beerapp.viewmodel.BeersRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    @Singleton
    fun provideRepo(punkEndpoint : PunkEndpoint) : BeersRepo {
        return BeersRepo(punkEndpoint)
    }

}