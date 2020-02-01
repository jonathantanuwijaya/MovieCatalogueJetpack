package yovi.putra.moviecatalogue.ui.tvshow.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.reactivex.Observable
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
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
import yovi.putra.moviecatalogue.data.entity.TVShowItem

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
    private val jsonPathItem = "sample/tvshow_item.json"
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

    @Test
    fun addFavorite() {
        val repository = JsonUtils.getJsonObject(jsonPathItem, TVShowItem::class.java)
        runBlocking {
            tvShowRepository.addFavorite(repository)
            Mockito.verify(tvShowRepository).addFavorite(repository)
        }
    }

    @Test
    fun getFavoriteStatus() {
        val repository = JsonUtils.getJsonObject(jsonPathItem, TVShowItem::class.java)
        val response = MutableLiveData<TVShowItem>()
        response.value = repository
        runBlocking {
            tvShowRepository.getFavorite(repository.id)
            Mockito.`when`(tvShowRepository.getFavoriteById(repository.id)).thenReturn(response)
            MatcherAssert.assertThat(response.value, CoreMatchers.equalTo(repository) )
        }
    }

    @Test
    fun deleteFavorite() {
        val repository = JsonUtils.getJsonObject(jsonPathItem, TVShowItem::class.java)
        runBlocking {
            tvShowRepository.deleteFavorite(repository.id)
            Mockito.verify(tvShowRepository).deleteFavorite(repository.id)
        }
    }
}