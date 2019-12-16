package yovi.putra.moviecatalogue.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yovi.putra.moviecatalogue.data.Movie
import yovi.putra.moviecatalogue.repository.DataRepository

class MovieVM(application: Application) : AndroidViewModel(application) {
    private var movieLiveData = MutableLiveData<MutableList<Movie>>()

    fun getMovie() : LiveData<MutableList<Movie>> {
        movieLiveData.postValue(DataRepository.getMovies(getApplication()))
        return movieLiveData
    }
}