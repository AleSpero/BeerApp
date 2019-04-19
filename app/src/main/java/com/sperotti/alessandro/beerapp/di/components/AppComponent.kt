package com.sperotti.alessandro.beerapp.di.components

import com.sperotti.alessandro.beerapp.di.modules.NetworkModule
import com.sperotti.alessandro.beerapp.di.modules.RepoModule
import com.sperotti.alessandro.beerapp.ui.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepoModule::class])
interface AppComponent {

    fun inject(activity : MainActivity)
}