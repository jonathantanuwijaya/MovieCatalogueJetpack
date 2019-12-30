package yovi.putra.moviecatalogue.ui.movie.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo
import yovi.putra.moviecatalogue.core.base.BaseViewModel
import yovi.putra.moviecatalogue.core.utils.state.LoaderState
import yovi.putra.moviecatalogue.core.utils.state.ResultState
import yovi.putra.moviecatalogue.data.repository.MovieRepository

class MovieFmViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {
    private var movie: MutableLiveData<ResultState>? = null

    fun getMovie(): LiveData<ResultState>? {
        movie?.let {
            loaderState.postValue(LoaderState.Hide)
        } ?: run {
            setMovie()
        }
        return movie
    }

    fun setMovie() {
        movie ?: run { movie = MutableLiveData() }
        loaderState.postValue(LoaderState.Show)
        movieRepository.getMovieList()
            .doOnTerminate { loaderState.postValue(LoaderState.Hide) }
            .subscribe(
                { movie?.postValue(ResultState.Success(it)) },
                { movie?.postValue(ResultState.Error(it)) }
            )
            .addTo(subscriber)
    }
}