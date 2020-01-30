package yovi.putra.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.Observable
import yovi.putra.moviecatalogue.core.common.Constant
import yovi.putra.moviecatalogue.core.utils.threat.EspressoIdlingResource
import yovi.putra.moviecatalogue.core.utils.threat.RxUtils
import yovi.putra.moviecatalogue.data.entity.TVShowDetailResponse
import yovi.putra.moviecatalogue.data.entity.TVShowItem
import yovi.putra.moviecatalogue.data.entity.TVShowListResponse
import yovi.putra.moviecatalogue.data.local.TVShowDao
import yovi.putra.moviecatalogue.data.remote.TVShowApi

class TVShowRepository(private val api: TVShowApi, private val local: TVShowDao) : FavoriteConstruct<TVShowItem> {
    fun getTVShowList() : Observable<TVShowListResponse> {
        return api.getTVShowList(Constant.API_KEY, 1)
            .compose(RxUtils.applyObservableAsync())
            .doOnSubscribe { EspressoIdlingResource.increment() }
            .doOnTerminate { EspressoIdlingResource.decrement() }
    }

    fun getTVShowDetail(id: Int) : Observable<TVShowDetailResponse> {
        return api.getTVShowDetail(id, Constant.API_KEY)
            .compose(RxUtils.applyObservableAsync())
            .doOnSubscribe { EspressoIdlingResource.increment() }
            .doOnTerminate { EspressoIdlingResource.decrement() }
    }

    override suspend fun addFavorite(data: TVShowItem) = local.insert(data)

    override suspend fun deleteFavorite(id: Int) = local.delete(id)

    override fun getFavoriteById(id: Int): LiveData<TVShowItem> = local.getTVShow(id)

    override fun getFavorite(page: Int): LiveData<PagedList<TVShowItem>> =
        LivePagedListBuilder(local.getTVShow(), page).build()

}