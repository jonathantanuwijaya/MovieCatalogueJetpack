package yovi.putra.moviecatalogue.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class TVShow(
    val original_name: String,
    val genre_ids: MutableList<String>?,
    val name: String?,
    val popularity: Double?,
    val origin_country: MutableList<String>?,
    val original_language: String?,
    val vote_count: Int?,
    val first_air_date: String?,
    val backdrop_path: String?,
    val id: Int,
    val vote_average: Double?,
    val overview: String?,
    val poster_path: String?
) : Parcelable