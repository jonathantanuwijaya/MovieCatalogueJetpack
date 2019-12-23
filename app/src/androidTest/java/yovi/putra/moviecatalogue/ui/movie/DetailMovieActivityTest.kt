package yovi.putra.moviecatalogue.ui.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.data.repository.MovieRepository
import yovi.putra.moviecatalogue.ui.MainActivity

@RunWith(AndroidJUnit4::class)
class DetailMovieActivityTest {
    @Rule
    @JvmField var activity = ActivityTestRule(MainActivity::class.java)
    private val movie = MovieRepository.getMovies()[0]

    @Before
    fun setUp() {
        DetailMovieActivity.navigate(activity.activity, movie.id)
    }

    @Test
    fun showDetailMovieTest() {
        onView(withId(R.id.toolbar_title)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbar_title)).check(matches(withText(movie.title)))

        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(movie.release_date)))

        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(withText(movie.vote_average)))

        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(withText(movie.overview)))

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
    }
}