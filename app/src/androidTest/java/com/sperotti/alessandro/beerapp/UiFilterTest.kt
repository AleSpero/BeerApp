package com.sperotti.alessandro.beerapp

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
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.allOf


@RunWith(AndroidJUnit4::class)
class UiFilterTest {

        @get:Rule
        var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

        @Test
        fun testWithFilters() {

            onEditTextWithinTilWithId(R.id.fromDate).perform(typeText("16/10/2010"))
            onEditTextWithinTilWithId(R.id.toDate).perform(typeText("16/10/2015"))

            onView(withId(R.id.search_btn)).perform(click())

            Thread.sleep(3000L) //TODO Hmmmm

            //TODO check recyclerview results

        }


        fun onEditTextWithinTilWithId(@IdRes textInputLayoutId: Int): ViewInteraction {
            return onView(allOf(isDescendantOfA(withId(textInputLayoutId)), isAssignableFrom(EditText::class.java)))
        }

}