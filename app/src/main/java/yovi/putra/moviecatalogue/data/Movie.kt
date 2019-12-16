package yovi.putra.moviecatalogue.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Movie(
    var icon: Int,
    var title: String?,
    var description: String?,
    var date: String?,
    var rating: String?
) : Parcelable