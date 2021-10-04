package com.sperotti.alessandro.beerapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sperotti.alessandro.beerapp.R
import com.sperotti.alessandro.beerapp.ui.fragments.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.elevation = 0f
        title = getString(R.string.app_name)

        if(savedInstanceState == null) {
            val fragmentManager = supportFragmentManager

            fragmentManager.beginTransaction()
                    .add(R.id.container, HomeFragment())
                    .commit()
        }
    }
}
