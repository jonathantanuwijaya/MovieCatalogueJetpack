package yovi.putra.moviecatalogue.ui.movie.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.refEq
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import retrofit2.Response
import yovi.putra.moviecatalogue.core.utils.state.LoaderState
import yovi.putra.moviecatalogue.core.utils.state.ResultState
import yovi.putra.moviecatalogue.data.entity.MovieListResponse
import yovi.putra.moviecatalogue.data.repository.MovieRepository
import yovi.putra.moviecatalogue.core.utils.json.JsonUtils

class MovieFmViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    lateinit var movieObserver: Observer<ResultState>
    @Mock
    lateinit var loaderObserver: Observer<LoaderState>
    @Mock
    lateinit var movieRepository: MovieRepository

    private lateinit var viewModel: MovieFmViewModel
    private val jsonPath = "sample/movie.json"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MovieFmViewModel(movieRepository)
    }

    @Test
    fun getMovieSuccess() {
        val repository = JsonUtils.getJsonObject(jsonPath, MovieListResponse::class.java)
        val movieResponse = MutableLiveData<ResultState>()

        `when`(movieRepository.getMovieList()).thenReturn(Observable.just(repository))
        movieResponse.value = ResultState.Success(repository)

        viewModel.getMovie()?.observeForever(movieObserver)
        verify(movieObserver).onChanged(refEq(movieResponse.value))
    }

    @Test
    fun getMovieError() {
        val movieResponse = MutableLiveData<ResultState>()
        val json = JsonUtils.getJson(jsonPath)
        val errorResponseBody = ResponseBody.create(MediaType.parse("application/json"), json)
        val errorResponse = Response.error<MovieListResponse>(404, errorResponseBody)
        val exception = HttpException(errorResponse)

        `when`(movieRepository.getMovieList()).thenReturn(Observable.error(exception))
        movieResponse.value = ResultState.Error(exception)

        viewModel.getMovie()?.observeForever(movieObserver)
        verify(movieObserver).onChanged(refEq(movieResponse.value))
    }

    @Test
    fun setMovieSuccess() {
        val movieResponse = MutableLiveData<ResultState>()
        val repository = JsonUtils.getJsonObject(jsonPath, MovieListResponse::class.java)

        `when`(movieRepository.getMovieList()).thenReturn(Observable.just(repository))
        movieResponse.value = ResultState.Success(repository)

        viewModel.setMovie()
        viewModel.getMovie()?.observeForever(movieObserver)
        verify(movieObserver).onChanged(refEq(movieResponse.value))
    }

    @Test
    fun setMovieError() {
        val movieResponse = MutableLiveData<ResultState>()
        val json = JsonUtils.getJson(jsonPath)
        val errorResponseBody = ResponseBody.create(MediaType.parse("application/json"), json)
        val errorResponse = Response.error<MovieListResponse>(404, errorResponseBody)
        val exception = HttpException(errorResponse)

        `when`(movieRepository.getMovieList()).thenReturn(Observable.error(exception))
        movieResponse.value = ResultState.Error(exception)

        viewModel.setMovie()
        viewModel.getMovie()?.observeForever(movieObserver)
        verify(movieObserver).onChanged(refEq(movieResponse.value))
    }

    @Test
    fun getMovieLoading() {
        val loaderState = MutableLiveData<LoaderState>()

        `when`(movieRepository.getMovieList()).thenReturn(Observable.empty())
        viewModel.loader.observeForever(loaderObserver)

        loaderState.postValue(LoaderState.Show)
        viewModel.getMovie()?.observeForever(movieObserver)
        verify(loaderObserver).onChanged(refEq(loaderState.value))

        loaderState.postValue(LoaderState.Hide)
        verify(loaderObserver).onChanged(refEq(loaderState.value))
    }

    @Test
    fun setMovieLoading() {
        val loaderState = MutableLiveData<LoaderState>()

        `when`(movieRepository.getMovieList()).thenReturn(Observable.empty())
        viewModel.loader.observeForever(loaderObserver)

        loaderState.postValue(LoaderState.Show)
        viewModel.setMovie()
        verify(loaderObserver).onChanged(refEq(loaderState.value))

        loaderState.postValue(LoaderState.Hide)
        verify(loaderObserver).onChanged(refEq(loaderState.value))
    }
}