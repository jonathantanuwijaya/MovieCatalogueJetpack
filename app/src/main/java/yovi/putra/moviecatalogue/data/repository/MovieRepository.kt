package yovi.putra.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.Observable
import yovi.putra.moviecatalogue.core.common.Constant
import yovi.putra.moviecatalogue.core.utils.threat.EspressoIdlingResource
import yovi.putra.moviecatalogue.core.utils.threat.RxUtils
import yovi.putra.moviecatalogue.data.entity.MovieDetailResponse
import yovi.putra.moviecatalogue.data.entity.MovieItem
import yovi.putra.moviecatalogue.data.entity.MovieListResponse
import yovi.putra.moviecatalogue.data.local.MovieDao
import yovi.putra.moviecatalogue.data.remote.MovieApi

open class MovieRepository(private val api: MovieApi, private val local: MovieDao) : FavoriteConstruct<MovieItem> {
    fun getMovieList() : Observable<MovieListResponse> {
        return api.getMovieList(Constant.API_KEY, 1)
            .compose(RxUtils.applyObservableAsync())
            .doOnSubscribe { EspressoIdlingResource.increment() }
            .doOnTerminate { EspressoIdlingResource.decrement() }
    }

    fun getMovieDetail(idMovie: Int) : Observable<MovieDetailResponse> {
        return api.getMovieDetail(idMovie, Constant.API_KEY)
            .compose(RxUtils.applyObservableAsync())
            .doOnSubscribe { EspressoIdlingResource.increment() }
            .doOnTerminate { EspressoIdlingResource.decrement() }
    }

    override suspend  fun addFavorite(data: MovieItem) {
        local.insert(data)
    }

    override suspend fun deleteFavorite(id: Int) {
        local.delete(id)
    }

    override fun getFavoriteById(id: Int): LiveData<MovieItem>? = local.getMovie(id)

    override fun getFavorite(page: Int): LiveData<PagedList<MovieItem>> {
        return LivePagedListBuilder(local.getMovie(), page).build()
    }
}