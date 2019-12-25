package yovi.putra.moviecatalogue.ui.movie.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo
import yovi.putra.moviecatalogue.core.base.BaseViewModel
import yovi.putra.moviecatalogue.core.utils.state.LoaderState
import yovi.putra.moviecatalogue.core.utils.state.ResultState
import yovi.putra.moviecatalogue.data.repository.MovieRepository

class MovieFmViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {
    private var movieResult = MutableLiveData<ResultState>()
    val movie: LiveData<ResultState>
        get() = movieResult

    init {
        reloadMovie()
    }

    fun reloadMovie() {
        loaderState.postValue(LoaderState.Show)
        movieRepository.getMovieList()
            .doOnTerminate { loaderState.postValue(LoaderState.Hide) }
            .subscribe(
                { movieResult.postValue(ResultState.Success(it)) },
                { movieResult.postValue(ResultState.Error(it)) }
            )
            .addTo(subscriber)
    }
}