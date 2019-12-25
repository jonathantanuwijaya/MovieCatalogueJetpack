package yovi.putra.moviecatalogue.ui.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo
import yovi.putra.moviecatalogue.core.base.BaseViewModel
import yovi.putra.moviecatalogue.core.utils.state.LoaderState
import yovi.putra.moviecatalogue.core.utils.state.ResultState
import yovi.putra.moviecatalogue.data.repository.MovieRepository

class DetailMovieViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {
    private var movieResult : MutableLiveData<ResultState>? = null

    fun getMovie(movieId: Int): LiveData<ResultState>? {
        if (movieResult == null) {
            movieResult = MutableLiveData()

            loaderState.postValue(LoaderState.Show)
            movieRepository.getMovieDetail(movieId)
                .doOnTerminate { loaderState.postValue(LoaderState.Hide) }
                .subscribe(
                    { movieResult?.postValue(ResultState.Success(it)) },
                    { movieResult?.postValue(ResultState.Error(it)) }
                )
                .addTo(subscriber)
        }
        return movieResult
    }
}