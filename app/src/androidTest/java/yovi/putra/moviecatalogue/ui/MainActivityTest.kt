package yovi.putra.moviecatalogue.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.core.utils.RecyclerViewItemCountAssertion
import yovi.putra.moviecatalogue.ui.dashboard.MainActivity

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField var activity = ActivityTestRule(MainActivity::class.java)

    @Test
    fun onNavigationItemSelected() {
        onView(withId(R.id.action_movie)).perform(click())
        Thread.sleep(1000)

        onView(withId(R.id.action_tvshow)).perform(click())
        Thread.sleep(1000)
    }

    @Test
    fun showMovieFragment() {
        onView(withId(R.id.action_movie)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.list_item)).check(matches(isDisplayed()))
        onView(withId(R.id.list_item)).check(RecyclerViewItemCountAssertion(12))
    }

    @Test
    fun showTvShowFragment() {
        onView(withId(R.id.action_tvshow)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.list_item)).check(matches(isDisplayed()))
        onView(withId(R.id.list_item)).check(RecyclerViewItemCountAssertion(14))
    }
}