package yovi.putra.moviecatalogue.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.refEq
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import yovi.putra.moviecatalogue.core.utils.json.JsonUtils
import yovi.putra.moviecatalogue.data.entity.MovieItem
import yovi.putra.moviecatalogue.data.entity.MovieListResponse
import yovi.putra.moviecatalogue.data.repository.MovieRepository
import yovi.putra.moviecatalogue.ui.utils.mockPagedList

class MovieFavoriteViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var movieRepository: MovieRepository
    @Mock
    private lateinit var observer: Observer<PagedList<MovieItem>>
    private lateinit var viewModel: MovieFavoriteViewModel
    private val jsonPath = "sample/movie.json"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MovieFavoriteViewModel(movieRepository)
    }

    @Test
    fun getMovie() {
        val movieResponse = MutableLiveData<PagedList<MovieItem>>()
        val repository = JsonUtils.getJsonObject(jsonPath, MovieListResponse::class.java)

        movieResponse.value = repository.results?.let { mockPagedList(it) }
        `when`(movieRepository.getFavorite(15)).thenReturn(movieResponse)

        viewModel.getMovie()?.observeForever(observer)
        verify(observer).onChanged(refEq(movieResponse.value))
    }
}