package yovi.putra.moviecatalogue.ui.favorite

import androidx.lifecycle.ViewModel
import com.yeputra.moviecatalogue.view.favorite.MovieFavoriteFm
import yovi.putra.moviecatalogue.ui.favorite.tvshow.TVShowFavoriteFm
import yovi.putra.moviecatalogue.core.adapter.VPager

class FavoriteViewModel : ViewModel() {
    val pagers = arrayListOf(
        VPager(MovieFavoriteFm::class.java.simpleName, MovieFavoriteFm()),
        VPager(TVShowFavoriteFm::class.java.simpleName, TVShowFavoriteFm())
    )
}