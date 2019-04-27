package com.sperotti.alessandro.beerapp

import android.view.View
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule;
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import com.sperotti.alessandro.beerapp.ui.activities.MainActivity
import org.junit.Rule
import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.sperotti.alessandro.beerapp.models.Beer
import com.sperotti.alessandro.beerapp.ui.adapters.BeerViewHolder
import kotlinx.android.synthetic.main.fragment_home.*
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.junit.Before


@RunWith(AndroidJUnit4::class)
class UiFilterTest {

        @get:Rule
        var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

        @Test
        fun testWithFilters() {

            onEditTextWithinTilWithId(R.id.fromDate).perform(typeText("16/10/2010"))
            onEditTextWithinTilWithId(R.id.toDate).perform(typeText("16/10/2015"), closeSoftKeyboard())

            onView(withId(R.id.search_btn)).perform(click())

            Thread.sleep(2000L)

            val numBeers = EspressoUtils.getCountFromRecyclerView(R.id.beerList)
            val recyclerViewMatcher = onView(withId(R.id.beerList))

            recyclerViewMatcher.perform(RecyclerViewActions.scrollToPosition<BeerViewHolder>(numBeers-1))

            for(i in 0 until numBeers){
                recyclerViewMatcher.perform(RecyclerViewActions.scrollToPosition<BeerViewHolder>(i), click())

                Thread.sleep(500)

                onView(withId(R.id.first_brewed))
                    .check(matches(
                        anyOf(
                        withSubstring("2010"),
                        withSubstring("2011"),
                        withSubstring("2012"),
                        withSubstring("2013"),
                        withSubstring("2014"),
                        withSubstring("2015")
                    )
                    ))

                onView(withText("OK")).perform(pressBack())

            }

        }


        fun onEditTextWithinTilWithId(@IdRes textInputLayoutId: Int): ViewInteraction {
            return onView(allOf(isDescendantOfA(withId(textInputLayoutId)), isAssignableFrom(EditText::class.java)))
        }

}