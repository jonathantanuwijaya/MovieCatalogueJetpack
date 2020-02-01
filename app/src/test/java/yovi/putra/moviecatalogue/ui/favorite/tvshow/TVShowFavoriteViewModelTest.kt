package yovi.putra.moviecatalogue.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import yovi.putra.moviecatalogue.core.utils.json.JsonUtils
import yovi.putra.moviecatalogue.data.entity.TVShowItem
import yovi.putra.moviecatalogue.data.entity.TVShowListResponse
import yovi.putra.moviecatalogue.data.repository.TVShowRepository
import yovi.putra.moviecatalogue.ui.utils.mockPagedList

class TVShowFavoriteViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var tvShowRepository: TVShowRepository
    @Mock
    private lateinit var observer: Observer<PagedList<TVShowItem>>
    private lateinit var viewModel: TVShowFavoriteViewModel
    private val jsonPath = "sample/tvshow.json"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = TVShowFavoriteViewModel(tvShowRepository)
    }

    @Test
    fun getTVShow() {
        val response = MutableLiveData<PagedList<TVShowItem>>()
        val repository = JsonUtils.getJsonObject(jsonPath, TVShowListResponse::class.java)

        response.value = repository.results.let { mockPagedList(it) }
        Mockito.`when`(tvShowRepository.getFavorite(15)).thenReturn(response)

        viewModel.getTVShow()?.observeForever(observer)
        Mockito.verify(observer).onChanged(ArgumentMatchers.refEq(response.value))
    }
}