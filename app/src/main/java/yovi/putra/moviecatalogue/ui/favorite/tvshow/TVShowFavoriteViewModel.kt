package yovi.putra.moviecatalogue.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import yovi.putra.moviecatalogue.core.base.BaseViewModel
import yovi.putra.moviecatalogue.core.utils.state.LoaderState
import yovi.putra.moviecatalogue.core.utils.state.ResultState
import yovi.putra.moviecatalogue.data.entity.TVShowItem
import yovi.putra.moviecatalogue.data.repository.TVShowRepository

class TVShowFavoriteViewModel(private val tvShowRepository: TVShowRepository) : BaseViewModel() {
    fun getTVShow(): LiveData<PagedList<TVShowItem>>? {
        return tvShowRepository.getFavorite(15)
    }
}