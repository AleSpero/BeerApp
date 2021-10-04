package com.sperotti.alessandro.beerapp.di.modules

import com.google.gson.Gson
import com.sperotti.alessandro.beerapp.network.PunkEndpoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson() : Gson = Gson()

    @Provides
    @Singleton
    fun provideRetrofit(gson : Gson) : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.punkapi.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideEndpoint(retrofit: Retrofit) : PunkEndpoint{
        return retrofit.create(PunkEndpoint::class.java)
    }

}