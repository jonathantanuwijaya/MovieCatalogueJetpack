package yovi.putra.moviecatalogue.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import yovi.putra.moviecatalogue.core.base.BaseViewModel
import yovi.putra.moviecatalogue.data.entity.MovieItem
import yovi.putra.moviecatalogue.data.repository.MovieRepository

class MovieFavoriteViewModel(private val movieRepository: MovieRepository)
    : BaseViewModel() {

    fun getMovie(): LiveData<PagedList<MovieItem>>? {
        return movieRepository.getFavorite(15)
    }
}