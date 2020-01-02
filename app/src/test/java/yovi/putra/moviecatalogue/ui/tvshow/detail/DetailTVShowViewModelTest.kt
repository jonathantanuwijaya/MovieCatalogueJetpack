package yovi.putra.moviecatalogue.ui.tvshow.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import retrofit2.Response
import yovi.putra.moviecatalogue.core.utils.state.LoaderState
import yovi.putra.moviecatalogue.core.utils.state.ResultState
import yovi.putra.moviecatalogue.data.entity.MovieDetailResponse
import yovi.putra.moviecatalogue.data.entity.TVShowDetailResponse
import yovi.putra.moviecatalogue.data.repository.TVShowRepository
import yovi.putra.moviecatalogue.core.utils.json.JsonUtils

class DetailTVShowViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    lateinit var tvShowObserver: Observer<ResultState>
    @Mock
    lateinit var loaderObserver: Observer<LoaderState>
    @Mock
    lateinit var tvShowRepository: TVShowRepository

    private lateinit var viewModel: DetailTVShowViewModel
    private val jsonPath = "sample/tvshow_detail.json"
    private val tvShowId = 71912

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailTVShowViewModel(tvShowRepository)
    }

    @Test
    fun getTVShowDetailSuccess() {
        val repository = JsonUtils.getJsonObject(jsonPath, TVShowDetailResponse::class.java)
        val movieResponse = MutableLiveData<ResultState>()

        Mockito.`when`(tvShowRepository.getTVShowDetail(repository.id))
            .thenReturn(Observable.just(repository))
        movieResponse.value = ResultState.Success(repository)

        viewModel.getTVShow(tvShowId)?.observeForever(tvShowObserver)
        Mockito.verify(tvShowObserver).onChanged(ArgumentMatchers.refEq(movieResponse.value))
    }

    @Test
    fun getTVShowDetailError() {
        val movieResponse = MutableLiveData<ResultState>()
        val json = JsonUtils.getJson(jsonPath)
        val errorResponseBody = ResponseBody.create(MediaType.parse("application/json"), json)
        val errorResponse = Response.error<MovieDetailResponse>(404, errorResponseBody)
        val exception = HttpException(errorResponse)

        Mockito.`when`(tvShowRepository.getTVShowDetail(tvShowId)).thenReturn(Observable.error(exception))
        movieResponse.value = ResultState.Error(exception)

        viewModel.getTVShow(tvShowId)?.observeForever(tvShowObserver)
        Mockito.verify(tvShowObserver).onChanged(ArgumentMatchers.refEq(movieResponse.value))
    }

    @Test
    fun getTVShowDetailLoading() {
        val loaderState = MutableLiveData<LoaderState>()

        Mockito.`when`(tvShowRepository.getTVShowDetail(tvShowId)).thenReturn(Observable.empty())
        viewModel.loader.observeForever(loaderObserver)

        loaderState.postValue(LoaderState.Show)
        viewModel.getTVShow(tvShowId)?.observeForever(tvShowObserver)
        Mockito.verify(loaderObserver).onChanged(ArgumentMatchers.refEq(loaderState.value))

        loaderState.postValue(LoaderState.Hide)
        Mockito.verify(loaderObserver).onChanged(ArgumentMatchers.refEq(loaderState.value))
    }
}