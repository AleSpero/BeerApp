package com.sperotti.alessandro.beerapp

import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.Espresso.onView
import androidx.annotation.IdRes
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.TypeSafeMatcher


class EspressoUtils {


    companion object {

        @JvmStatic
        fun atPosition(position: Int, @NonNull itemMatcher: Matcher<View>): Matcher<View> {
            checkNotNull(itemMatcher)
            return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

                override fun describeTo(description: Description) {
                    description.appendText("has item at position $position: ")
                    itemMatcher.describeTo(description)
                }

                override fun matchesSafely(view: RecyclerView): Boolean {
                    val viewHolder = view.findViewHolderForAdapterPosition(position)
                        ?: // has no item on such position
                        return false
                    return itemMatcher.matches(viewHolder.itemView)
                }
            }
        }

        fun getCountFromRecyclerView(@IdRes RecyclerViewId: Int): Int {
            var count = 0
            val matcher = object : TypeSafeMatcher<View>() {
                override fun matchesSafely(item: View): Boolean {
                    count = (item as RecyclerView).adapter!!.itemCount
                    return true
                }

                override fun describeTo(description: Description) {}
            }
            onView(allOf(withId(RecyclerViewId), isDisplayed())).check(matches(matcher))
            val result = count
            count = 0
            return result
        }


    }

}