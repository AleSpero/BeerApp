package com.sperotti.alessandro.beerapp.di.modules

import com.sperotti.alessandro.beerapp.viewmodel.BeersRepo
import dagger.Module


@Module
class RepoModule {

    fun provideRepo() : BeersRepo {
        return BeersRepo()
    }

}