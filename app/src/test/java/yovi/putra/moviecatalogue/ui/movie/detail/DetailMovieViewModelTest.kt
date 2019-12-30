package yovi.putra.moviecatalogue.ui.movie.detail

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
import yovi.putra.moviecatalogue.data.entity.MovieDetailResponse
import yovi.putra.moviecatalogue.data.repository.MovieRepository
import yovi.putra.moviecatalogue.ui.utils.JsonUtils

class DetailMovieViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    lateinit var movieObserver: Observer<ResultState>
    @Mock
    lateinit var loaderObserver: Observer<LoaderState>
    @Mock
    lateinit var movieRepository: MovieRepository
    lateinit var viewModel: DetailMovieViewModel

    private val jsonPath = "sample/movie_detail.json"
    private val movieId = 512200

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailMovieViewModel(movieRepository)
    }

    @Test
    fun getMovieDetailSuccess() {
        val repository = JsonUtils.getJsonObject(jsonPath, MovieDetailResponse::class.java)
        val movieResponse = MutableLiveData<ResultState>()

        `when`(movieRepository.getMovieDetail(repository.id))
            .thenReturn(Observable.just(repository))
        movieResponse.value = ResultState.Success(repository)

        viewModel.getMovie(movieId)?.observeForever(movieObserver)
        verify(movieObserver).onChanged(refEq(movieResponse.value))
    }

    @Test
    fun getMovieDetailError() {
        val movieResponse = MutableLiveData<ResultState>()
        val json = JsonUtils.getJson(jsonPath)
        val errorResponseBody = ResponseBody.create(MediaType.parse("application/json"), json)
        val errorResponse = Response.error<MovieDetailResponse>(404, errorResponseBody)
        val exception = HttpException(errorResponse)

        `when`(movieRepository.getMovieDetail(movieId)).thenReturn(Observable.error(exception))
        movieResponse.value = ResultState.Error(exception)

        viewModel.getMovie(movieId)?.observeForever(movieObserver)
        verify(movieObserver).onChanged(refEq(movieResponse.value))
    }

    @Test
    fun getMovieDetailLoading() {
        val loaderState = MutableLiveData<LoaderState>()

        `when`(movieRepository.getMovieDetail(movieId)).thenReturn(Observable.empty())
        viewModel.loader.observeForever(loaderObserver)

        loaderState.postValue(LoaderState.Show)
        viewModel.getMovie(movieId)?.observeForever(movieObserver)
        verify(loaderObserver).onChanged(refEq(loaderState.value))

        loaderState.postValue(LoaderState.Hide)
        verify(loaderObserver).onChanged(refEq(loaderState.value))
    }
}