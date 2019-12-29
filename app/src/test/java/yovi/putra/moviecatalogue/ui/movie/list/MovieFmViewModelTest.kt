package yovi.putra.moviecatalogue.ui.movie.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.refEq
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import yovi.putra.moviecatalogue.core.utils.state.LoaderState
import yovi.putra.moviecatalogue.core.utils.state.ResultState
import yovi.putra.moviecatalogue.data.remote.MovieApi
import yovi.putra.moviecatalogue.data.repository.MovieRepository

class MovieFmViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    lateinit var movieObserver: Observer<ResultState>
    @Mock
    lateinit var loaderObserver: Observer<LoaderState>
    @Mock
    lateinit var movieApi: MovieApi
    lateinit var viewModel: MovieFmViewModel
    lateinit var movieRepository: MovieRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        movieRepository = MovieRepository(movieApi)
        viewModel = MovieFmViewModel(movieRepository)
    }

    @Test
    fun getMovieLoading() {
        val loaderState = MutableLiveData<LoaderState>()
        loaderState.postValue(LoaderState.Show)
        viewModel.loader.observeForever(loaderObserver)

        `when`(movieRepository.getMovieList()).thenReturn(Observable.empty())
        viewModel.getMovie()?.observeForever(movieObserver)

        verify(loaderObserver).onChanged(refEq(loaderState.value))
    }

    @Test
    fun reloadMovie() {
    }
}