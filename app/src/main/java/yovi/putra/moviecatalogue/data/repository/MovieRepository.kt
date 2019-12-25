package yovi.putra.moviecatalogue.data.repository

import yovi.putra.moviecatalogue.core.common.Constant
import yovi.putra.moviecatalogue.core.utils.threat.RxUtils
import yovi.putra.moviecatalogue.data.entity.MovieDetailResponse
import yovi.putra.moviecatalogue.data.entity.MovieListResponse
import yovi.putra.moviecatalogue.data.remote.MovieApi

class MovieRepository(private val api: MovieApi) {
    fun getMovieList() : io.reactivex.Observable<MovieListResponse> {
        return api.getMovieList(Constant.API_KEY, 1).compose(RxUtils.applyObservableAsync())
    }

    fun getMovieDetail(idMovie: Int) : io.reactivex.Observable<MovieDetailResponse> {
        return api.getMovieDetail(idMovie, Constant.API_KEY).compose(RxUtils.applyObservableAsync())
    }
}