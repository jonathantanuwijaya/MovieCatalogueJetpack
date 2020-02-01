package yovi.putra.moviecatalogue.ui.favorite

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import yovi.putra.moviecatalogue.ui.favorite.movie.MovieFavoriteFm
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.core.adapter.VPager
import yovi.putra.moviecatalogue.ui.favorite.tvshow.TVShowFavoriteFm

class FavoriteViewModel : ViewModel() {
    var page: MutableLiveData<ArrayList<VPager>>? = null

    fun pagers(context: Context): MutableLiveData<ArrayList<VPager>>? {
        if (page == null) {
            page = MutableLiveData()
            val pager = ArrayList<VPager>()
            pager.add(VPager(context.getString(R.string.movie_favorite), MovieFavoriteFm()))
            pager.add(VPager(context.getString(R.string.tvshow_favorite), TVShowFavoriteFm()))
            page?.postValue(pager)
        }
        return page
    }
}