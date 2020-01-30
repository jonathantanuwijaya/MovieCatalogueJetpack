package yovi.putra.moviecatalogue.ui.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.reactivex.rxkotlin.addTo
import kotlinx.coroutines.launch
import yovi.putra.moviecatalogue.core.base.BaseViewModel
import yovi.putra.moviecatalogue.core.utils.state.LoaderState
import yovi.putra.moviecatalogue.core.utils.state.ResultState
import yovi.putra.moviecatalogue.data.entity.MovieItem
import yovi.putra.moviecatalogue.data.repository.MovieRepository

class DetailMovieViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {
    private var movieResult : MutableLiveData<ResultState>? = null

    fun getMovie(movieId: Int): LiveData<ResultState>? {
        movieResult ?: run {
            movieResult = MutableLiveData()
            movieRepository.getMovieDetail(movieId)
                .doOnSubscribe { loaderState.postValue(LoaderState.Show) }
                .doOnTerminate { loaderState.postValue(LoaderState.Hide) }
                .subscribe(
                    { movieResult?.postValue(ResultState.Success(it)) },
                    { movieResult?.postValue(ResultState.Error(it)) }
                )
                .addTo(subscriber)
        }

        return movieResult
    }

    fun addFavorite(data: MovieItem) = viewModelScope.launch {
        movieRepository.addFavorite(data)
    }

    fun deleteFavorite(data: MovieItem) = viewModelScope.launch {
        movieRepository.deleteFavorite(data.id)
    }

    fun isFavorited(id: Int) : LiveData<MovieItem>? = movieRepository.getFavoriteById(id)
}