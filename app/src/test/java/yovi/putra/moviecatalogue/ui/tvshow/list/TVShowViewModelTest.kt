package yovi.putra.moviecatalogue.ui.tvshow.list

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
import yovi.putra.moviecatalogue.data.entity.TVShowListResponse
import yovi.putra.moviecatalogue.data.repository.TVShowRepository
import yovi.putra.moviecatalogue.ui.utils.JsonUtils

class TVShowViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    lateinit var tvShowObserver: Observer<ResultState>
    @Mock
    lateinit var loaderObserver: Observer<LoaderState>
    @Mock
    lateinit var tvShowRepository: TVShowRepository
    lateinit var viewModel: TVShowViewModel
    private val jsonPath = "sample/tvshow.json"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = TVShowViewModel(tvShowRepository)
    }

    @Test
    fun getTVShowSuccess() {
        val repository = JsonUtils.getJsonObject(jsonPath, TVShowListResponse::class.java)
        val tvShowResponse = MutableLiveData<ResultState>()

        Mockito.`when`(tvShowRepository.getTVShowList()).thenReturn(Observable.just(repository))
        tvShowResponse.value = ResultState.Success(repository)

        viewModel.getTVShow()?.observeForever(tvShowObserver)
        Mockito.verify(tvShowObserver).onChanged(ArgumentMatchers.refEq(tvShowResponse.value))
    }

    @Test
    fun getTVShowError() {
        val tvShowResponse = MutableLiveData<ResultState>()
        val json = JsonUtils.getJson(jsonPath)
        val errorResponseBody = ResponseBody.create(MediaType.parse("application/json"), json)
        val errorResponse = Response.error<TVShowListResponse>(404, errorResponseBody)
        val exception = HttpException(errorResponse)

        Mockito.`when`(tvShowRepository.getTVShowList()).thenReturn(Observable.error(exception))
        tvShowResponse.value = ResultState.Error(exception)

        viewModel.getTVShow()?.observeForever(tvShowObserver)
        Mockito.verify(tvShowObserver).onChanged(ArgumentMatchers.refEq(tvShowResponse.value))
    }

    @Test
    fun setTVShowSuccess() {
        val tvShowResponse = MutableLiveData<ResultState>()
        val repository = JsonUtils.getJsonObject(jsonPath, TVShowListResponse::class.java)

        Mockito.`when`(tvShowRepository.getTVShowList()).thenReturn(Observable.just(repository))
        tvShowResponse.value = ResultState.Success(repository)

        viewModel.setTVShow()
        viewModel.getTVShow()?.observeForever(tvShowObserver)
        Mockito.verify(tvShowObserver).onChanged(ArgumentMatchers.refEq(tvShowResponse.value))
    }

    @Test
    fun setTVShowError() {
        val tvShowResponse = MutableLiveData<ResultState>()
        val json = JsonUtils.getJson(jsonPath)
        val errorResponseBody = ResponseBody.create(MediaType.parse("application/json"), json)
        val errorResponse = Response.error<TVShowListResponse>(404, errorResponseBody)
        val exception = HttpException(errorResponse)

        Mockito.`when`(tvShowRepository.getTVShowList()).thenReturn(Observable.error(exception))
        tvShowResponse.value = ResultState.Error(exception)

        viewModel.setTVShow()
        viewModel.getTVShow()?.observeForever(tvShowObserver)
        Mockito.verify(tvShowObserver).onChanged(ArgumentMatchers.refEq(tvShowResponse.value))
    }

    @Test
    fun getTVShowLoading() {
        val loaderState = MutableLiveData<LoaderState>()

        Mockito.`when`(tvShowRepository.getTVShowList()).thenReturn(Observable.empty())
        viewModel.loader.observeForever(loaderObserver)

        loaderState.postValue(LoaderState.Show)
        viewModel.getTVShow()?.observeForever(tvShowObserver)
        Mockito.verify(loaderObserver).onChanged(ArgumentMatchers.refEq(loaderState.value))

        loaderState.postValue(LoaderState.Hide)
        Mockito.verify(loaderObserver).onChanged(ArgumentMatchers.refEq(loaderState.value))
    }

    @Test
    fun setTVShowLoading() {
        val loaderState = MutableLiveData<LoaderState>()

        Mockito.`when`(tvShowRepository.getTVShowList()).thenReturn(Observable.empty())
        viewModel.loader.observeForever(loaderObserver)

        loaderState.postValue(LoaderState.Show)
        viewModel.setTVShow()
        Mockito.verify(loaderObserver).onChanged(ArgumentMatchers.refEq(loaderState.value))

        loaderState.postValue(LoaderState.Hide)
        Mockito.verify(loaderObserver).onChanged(ArgumentMatchers.refEq(loaderState.value))
    }
}