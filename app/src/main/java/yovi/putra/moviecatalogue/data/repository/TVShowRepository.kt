package yovi.putra.moviecatalogue.data.repository

import io.reactivex.Observable
import yovi.putra.moviecatalogue.core.common.Constant
import yovi.putra.moviecatalogue.core.utils.threat.RxUtils
import yovi.putra.moviecatalogue.data.entity.TVShowDetailResponse
import yovi.putra.moviecatalogue.data.entity.TVShowListResponse
import yovi.putra.moviecatalogue.data.remote.TVShowApi

class TVShowRepository(private val api: TVShowApi) {
    fun getTVShowList() : Observable<TVShowListResponse> {
        return api.getTVShowList(Constant.API_KEY, 1).compose(RxUtils.applyObservableAsync())
    }

    fun getTVShowDetail(id: Int) : Observable<TVShowDetailResponse> {
        return api.getTVShowDetail(id, Constant.API_KEY).compose(RxUtils.applyObservableAsync())
    }
}