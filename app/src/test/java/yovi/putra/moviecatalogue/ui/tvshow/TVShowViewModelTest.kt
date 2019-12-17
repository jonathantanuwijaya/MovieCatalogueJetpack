package yovi.putra.moviecatalogue.ui.tvshow

import com.google.gson.Gson
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import yovi.putra.moviecatalogue.data.entity.Movie
import yovi.putra.moviecatalogue.data.entity.TVShow

class TVShowViewModelTest {

    private lateinit var tvShowVM: TVShowViewModel
    private lateinit var tvShow: TVShow

    @Before
    fun setUp() {
        tvShowVM = TVShowViewModel()
        tvShow = Gson().fromJson("" +
                "  {\n" +
                "    \"original_name\": \"Supernatural\",\n" +
                "    \"genre_ids\": [\n" +
                "      18,\n" +
                "      9648,\n" +
                "      10765\n" +
                "    ],\n" +
                "    \"name\": \"Supernatural\",\n" +
                "    \"popularity\": 342.774,\n" +
                "    \"origin_country\": [\n" +
                "      \"US\"\n" +
                "    ],\n" +
                "    \"vote_count\": 2019,\n" +
                "    \"first_air_date\": \"2005-09-13\",\n" +
                "    \"backdrop_path\": \"/o9OKe3M06QMLOzTl3l6GStYtnE9.jpg\",\n" +
                "    \"original_language\": \"en\",\n" +
                "    \"id\": 1622,\n" +
                "    \"vote_average\": 7.4,\n" +
                "    \"overview\": \"When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way. \",\n" +
                "    \"poster_path\": \"/KoYWXbnYuS3b0GyQPkbuexlVK9.jpg\"\n" +
                "  }", TVShow::class.java)
    }

    @Test
    fun getTVShow() {
        val data = tvShowVM.getTvShow()
        assertNotNull(data)
        assertNotEquals(emptyList<Movie>(), data)
        assertEquals(20, data.size)
    }

    @Test
    fun getTVShowById() {
        val data = tvShowVM.getTvShow(tvShow.id)
        assertNotNull(data)
        assertEquals(tvShow.id, data?.id)
        assertEquals(tvShow.poster_path, data?.poster_path)
        assertEquals(tvShow.original_language, data?.original_language)
        assertEquals(tvShow.original_name, data?.original_name)
        assertEquals(tvShow.overview, data?.overview)
        assertEquals(tvShow.vote_average, data?.vote_average)
        assertEquals(tvShow.backdrop_path, data?.backdrop_path)
        assertEquals(tvShow.first_air_date, data?.first_air_date)
        assertEquals(tvShow.genre_ids, data?.genre_ids)
        assertEquals(tvShow.name, data?.name)
        assertEquals(tvShow.origin_country, data?.origin_country)
        assertEquals(tvShow.popularity, data?.popularity)
        assertEquals(tvShow.vote_count, data?.vote_count)
    }
}


















