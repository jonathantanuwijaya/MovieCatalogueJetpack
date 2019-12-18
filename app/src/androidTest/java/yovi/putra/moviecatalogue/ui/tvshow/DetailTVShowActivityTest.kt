package yovi.putra.moviecatalogue.ui.tvshow

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.data.repository.MovieRepository
import yovi.putra.moviecatalogue.data.repository.TVShowRepository
import yovi.putra.moviecatalogue.ui.MainActivity
import yovi.putra.moviecatalogue.ui.movie.DetailMovieActivity

@RunWith(AndroidJUnit4::class)
class DetailTVShowActivityTest {
    @Rule
    @JvmField var activity = ActivityTestRule(MainActivity::class.java)
    private val tvShow = TVShowRepository.getTVShow()[0]

    @Before
    fun setUp() {
        DetailTVShowActivity.navigate(activity.activity, tvShow.id)
    }

    @Test
    fun showDetailTVShowTest() {
        onView(withId(R.id.toolbar_title)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbar_title)).check(matches(withText(tvShow.name)))

        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(tvShow.first_air_date)))

        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(withText(tvShow.vote_average.toString())))

        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(withText(tvShow.overview)))

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.img_banner)).check(matches(isDisplayed()))
    }
}