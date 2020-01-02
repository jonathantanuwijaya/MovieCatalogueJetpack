package yovi.putra.moviecatalogue.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.core.utils.threat.EspressoIdlingResource.espressoIdlingResource
import yovi.putra.moviecatalogue.ui.dashboard.DashboardActivity


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField var activity = ActivityTestRule(DashboardActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(espressoIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(espressoIdlingResource)
    }

    @Test
    fun onNavigationItemSelected() {
        onView(withId(R.id.action_movie)).perform(click())
        onView(withId(R.id.action_tvshow)).perform(click())
    }

    @Test
    fun showMovieFragment() {
        onView(withId(R.id.action_movie)).perform(click())
        onView(withId(R.id.list_item)).check(matches(isDisplayed()))
    }

    @Test
    fun showTvShowFragment() {
        onView(withId(R.id.action_tvshow)).perform(click())
        onView(withId(R.id.list_item_tvshow)).check(matches(isDisplayed()))
    }
}