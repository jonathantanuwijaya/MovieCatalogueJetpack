package yovi.putra.moviecatalogue.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Movie(
    val id: Int,
    val title: String?,
    val poster_path: String?,
    val original_language: String?,
    val original_title: String,
    val vote_average: String?,
    val overview: String?,
    val release_date: String?
) : Parcelable