package com.sperotti.alessandro.beerapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.sperotti.alessandro.beerapp.R
import com.sperotti.alessandro.beerapp.di.components.DaggerAppComponent
import com.sperotti.alessandro.beerapp.di.modules.NetworkModule
import com.sperotti.alessandro.beerapp.ui.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager

        fragmentManager.beginTransaction()
            .add(R.id.container, HomeFragment())
            .commit()
    }
}
