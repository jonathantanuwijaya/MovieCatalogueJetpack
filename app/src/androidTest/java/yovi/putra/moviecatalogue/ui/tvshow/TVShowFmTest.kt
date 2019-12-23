package yovi.putra.moviecatalogue.ui.tvshow

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.ui.MainActivity
import yovi.putra.moviecatalogue.core.utils.RecyclerViewItemCountAssertion

@RunWith(AndroidJUnit4::class)
class TVShowFmTest {
    @Rule
    @JvmField var activity = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        onView(withId(R.id.action_tvshow)).perform(click())
    }

    @Test
    fun showTVShowTest() {
        onView(withId(R.id.list_item)).apply {
            check(matches(isDisplayed()))
            check(RecyclerViewItemCountAssertion(14))
            perform(scrollToPosition<RecyclerView.ViewHolder>(6))
            perform(scrollToPosition<RecyclerView.ViewHolder>(0))
            perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        }
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
    }
}