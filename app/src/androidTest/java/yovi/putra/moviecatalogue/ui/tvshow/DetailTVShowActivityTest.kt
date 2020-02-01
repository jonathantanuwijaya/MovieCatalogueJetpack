package yovi.putra.moviecatalogue.ui.tvshow

import android.content.Intent
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
import yovi.putra.moviecatalogue.core.utils.threat.EspressoIdlingResource
import yovi.putra.moviecatalogue.data.entity.TVShowItem
import yovi.putra.moviecatalogue.data.repository.DummyRepository
import yovi.putra.moviecatalogue.ui.tvshow.detail.DetailTVShowActivity

@RunWith(AndroidJUnit4::class)
class DetailTVShowActivityTest {
    @Rule
    @JvmField var activity = ActivityTestRule(DetailTVShowActivity::class.java)
    private lateinit var tvShow: TVShowItem

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
        tvShow = DummyRepository.getTVShowItem()
        activity.launchActivity(
            Intent().apply {
                putExtra(DetailTVShowActivity.TVSHOW, tvShow)
            }
        )
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Test
    fun showDetailTVShowTest() {
        onView(withId(R.id.toolbar_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.img_banner)).check(matches(isDisplayed()))
    }

    @Test
    fun selectIconFavorite() {
        onView(withId(R.id.bt_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.bt_favorite)).perform(click())
        onView(withId(R.id.bt_favorite)).perform(click())
    }
}