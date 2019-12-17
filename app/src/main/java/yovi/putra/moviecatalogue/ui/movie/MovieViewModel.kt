package yovi.putra.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import yovi.putra.moviecatalogue.data.entity.Movie
import yovi.putra.moviecatalogue.data.repository.MovieRepository

class MovieViewModel : ViewModel() {
    fun getMovie() : MutableList<Movie> {
        return MovieRepository.getMovies()
    }

    fun getMovieById(id: Int) = MovieRepository.getMovieById(id)
}