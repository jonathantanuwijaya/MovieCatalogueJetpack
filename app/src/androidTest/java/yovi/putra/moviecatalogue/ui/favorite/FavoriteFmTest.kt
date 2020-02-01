package yovi.putra.moviecatalogue.ui.favorite

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.core.utils.threat.EspressoIdlingResource
import yovi.putra.moviecatalogue.ui.dashboard.DashboardActivity

class FavoriteFmTest {

    @Rule
    @JvmField var activity = ActivityTestRule(DashboardActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
        onView(withId(R.id.action_favorite)).perform(ViewActions.click())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Test
    @Throws(Exception::class)
    fun onNavigationItemSelected() {
        onView(withText(R.string.movie_favorite)).perform(ViewActions.click())
        onView(withId(R.id.list_item_favorite)).check(matches(isDisplayed()))

        onView(withText(R.string.tvshow_favorite)).perform(ViewActions.click())
        onView(withId(R.id.list_item_tvshow_favorite)).check(matches(isDisplayed()))
    }
}