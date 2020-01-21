package yovi.putra.moviecatalogue.ui.dashboard

import androidx.lifecycle.ViewModel
import yovi.putra.moviecatalogue.core.adapter.VPager
import yovi.putra.moviecatalogue.ui.favorite.FavoriteFm
import yovi.putra.moviecatalogue.ui.movie.list.MovieFm
import yovi.putra.moviecatalogue.ui.tvshow.list.TVShowFm

class DashboardViewModel : ViewModel() {
    val pagers = arrayListOf(
        VPager(MovieFm::class.java.simpleName, MovieFm()),
        VPager(TVShowFm::class.java.simpleName, TVShowFm()),
        VPager(FavoriteFm::class.java.simpleName, FavoriteFm())
    )
}