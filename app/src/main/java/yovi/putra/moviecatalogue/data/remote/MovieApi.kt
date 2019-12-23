package yovi.putra.moviecatalogue.data.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import yovi.putra.moviecatalogue.data.entity.MovieDetailResponse
import yovi.putra.moviecatalogue.data.entity.MovieListResponse

interface MovieApi {
    @GET("3/movie/upcoming")
    fun getMovieList(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Observable<MovieListResponse>

    @GET("3/movie/{idMovie}")
    fun getMovieDetail(
        @Path("idMovie") idMovie: Int,
        @Query("api_key") api_key: String
    ): Observable<MovieDetailResponse>
}