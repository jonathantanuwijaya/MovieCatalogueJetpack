package yovi.putra.moviecatalogue.ui.tvshow.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.reactivex.rxkotlin.addTo
import kotlinx.coroutines.launch
import yovi.putra.moviecatalogue.core.base.BaseViewModel
import yovi.putra.moviecatalogue.core.utils.state.LoaderState
import yovi.putra.moviecatalogue.core.utils.state.ResultState
import yovi.putra.moviecatalogue.data.entity.TVShowItem
import yovi.putra.moviecatalogue.data.repository.TVShowRepository

class DetailTVShowViewModel(private val tvShowRepository: TVShowRepository) : BaseViewModel() {
    private var tvShowResult : MutableLiveData<ResultState>? = null

    fun getTVShow(tvShowId: Int): LiveData<ResultState>? {
        tvShowResult ?: run {
            tvShowResult = MutableLiveData()
            tvShowRepository.getTVShowDetail(tvShowId)
                .doOnSubscribe { loaderState.postValue(LoaderState.Show) }
                .doOnTerminate { loaderState.postValue(LoaderState.Hide) }
                .subscribe(
                    { tvShowResult?.postValue(ResultState.Success(it)) },
                    { tvShowResult?.postValue(ResultState.Error(it)) }
                )
                .addTo(subscriber)
        }
        return tvShowResult
    }

    fun addFavorite(data: TVShowItem) = viewModelScope.launch {
        tvShowRepository.addFavorite(data)
    }

    fun deleteFavorite(data: TVShowItem) = viewModelScope.launch {
        tvShowRepository.deleteFavorite(data.id)
    }

    fun isFavorited(id: Int) : LiveData<TVShowItem>? = tvShowRepository.getFavoriteById(id)
}