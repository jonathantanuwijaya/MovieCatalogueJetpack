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
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import retrofit2.Response
import yovi.putra.moviecatalogue.core.utils.json.JsonUtils
import yovi.putra.moviecatalogue.core.utils.state.LoaderState
import yovi.putra.moviecatalogue.core.utils.state.ResultState
import yovi.putra.moviecatalogue.data.entity.TVShowListResponse
import yovi.putra.moviecatalogue.data.repository.TVShowRepository

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

    private lateinit var viewModel: TVShowFmViewModel
    private val jsonPath = "sample/tvshow.json"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = TVShowFmViewModel(tvShowRepository)
    }

    @Test
    fun getTVShowSuccess() {
        val repository = JsonUtils.getJsonObject(jsonPath, TVShowListResponse::class.java)
        val tvShowResponse = MutableLiveData<ResultState>()

        `when`(tvShowRepository.getTVShowList()).thenReturn(Observable.just(repository))
        tvShowResponse.value = ResultState.Success(repository)

        viewModel.getTVShow()?.observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(ArgumentMatchers.refEq(tvShowResponse.value))
    }

    @Test
    fun getTVShowError() {
        val tvShowResponse = MutableLiveData<ResultState>()
        val json = JsonUtils.getJson(jsonPath)
        val errorResponseBody = ResponseBody.create(MediaType.parse("application/json"), json)
        val errorResponse = Response.error<TVShowListResponse>(404, errorResponseBody)
        val exception = HttpException(errorResponse)

        `when`(tvShowRepository.getTVShowList()).thenReturn(Observable.error(exception))
        tvShowResponse.value = ResultState.Error(exception)

        viewModel.getTVShow()?.observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(ArgumentMatchers.refEq(tvShowResponse.value))
    }

    @Test
    fun setTVShowSuccess() {
        val tvShowResponse = MutableLiveData<ResultState>()
        val repository = JsonUtils.getJsonObject(jsonPath, TVShowListResponse::class.java)

        `when`(tvShowRepository.getTVShowList()).thenReturn(Observable.just(repository))
        tvShowResponse.value = ResultState.Success(repository)

        viewModel.setTVShow()
        viewModel.getTVShow()?.observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(ArgumentMatchers.refEq(tvShowResponse.value))
    }

    @Test
    fun setTVShowError() {
        val tvShowResponse = MutableLiveData<ResultState>()
        val json = JsonUtils.getJson(jsonPath)
        val errorResponseBody = ResponseBody.create(MediaType.parse("application/json"), json)
        val errorResponse = Response.error<TVShowListResponse>(404, errorResponseBody)
        val exception = HttpException(errorResponse)

        `when`(tvShowRepository.getTVShowList()).thenReturn(Observable.error(exception))
        tvShowResponse.value = ResultState.Error(exception)

        viewModel.setTVShow()
        viewModel.getTVShow()?.observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(ArgumentMatchers.refEq(tvShowResponse.value))
    }

    @Test
    fun getTVShowLoading() {
        val loaderState = MutableLiveData<LoaderState>()

        `when`(tvShowRepository.getTVShowList()).thenReturn(Observable.empty())
        viewModel.loader.observeForever(loaderObserver)

        loaderState.postValue(LoaderState.Show)
        viewModel.getTVShow()?.observeForever(tvShowObserver)
        verify(loaderObserver).onChanged(ArgumentMatchers.refEq(loaderState.value))

        loaderState.postValue(LoaderState.Hide)
        verify(loaderObserver).onChanged(ArgumentMatchers.refEq(loaderState.value))
    }

    @Test
    fun setTVShowLoading() {
        val loaderState = MutableLiveData<LoaderState>()

        `when`(tvShowRepository.getTVShowList()).thenReturn(Observable.empty())
        viewModel.loader.observeForever(loaderObserver)

        loaderState.postValue(LoaderState.Show)
        viewModel.setTVShow()
        verify(loaderObserver).onChanged(ArgumentMatchers.refEq(loaderState.value))

        loaderState.postValue(LoaderState.Hide)
        verify(loaderObserver).onChanged(ArgumentMatchers.refEq(loaderState.value))
    }
}