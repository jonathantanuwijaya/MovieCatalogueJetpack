package yovi.putra.moviecatalogue.data.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import yovi.putra.moviecatalogue.data.entity.TVShowDetailResponse
import yovi.putra.moviecatalogue.data.entity.TVShowListResponse

interface TVShowApi {
    @GET("3/tv/popular")
    fun getTVShowList(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ) : Observable<TVShowListResponse>

    @GET("3/tv/{id}")
    fun getTVShowDetail(
        @Path("id") id: Int,
        @Query("api_key") api_key: String
    ) : Observable<TVShowDetailResponse>
}