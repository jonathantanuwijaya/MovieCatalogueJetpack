package yovi.putra.moviecatalogue.ui.favorite.tvshow

import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_movie.swiperefresh
import kotlinx.android.synthetic.main.fragment_tvshow_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.core.base.BaseFragment
import yovi.putra.moviecatalogue.data.entity.TVShowItem
import yovi.putra.moviecatalogue.ui.tvshow.detail.DetailTVShowActivity

class TVShowFavoriteFm : BaseFragment() {

    private lateinit var adapter: TVShowFavoriteAdapter
    private val tvShowVM: TVShowFavoriteViewModel by viewModel()

    override fun setupLayoutId(): Int = R.layout.fragment_tvshow_favorite

    override fun setupData() {
        adapter =
            TVShowFavoriteAdapter { tvshow ->
                DetailTVShowActivity.navigate(
                    contextView,
                    tvshow
                )
            }

        tvShowVM.getTVShow()?.observe(this, tvShowObserve)
    }

    override fun setupUI() {
        swiperefresh.setColorSchemeColors(ContextCompat.getColor(contextView, R.color.colorAccent))
        swiperefresh.setOnRefreshListener {
            tvShowVM.getTVShow()?.observe(this, tvShowObserve)
        }
        list_item_tvshow_favorite.layoutManager = GridLayoutManager(contextView, 2)
        list_item_tvshow_favorite.overScrollMode = View.OVER_SCROLL_NEVER
        list_item_tvshow_favorite.adapter = adapter
    }

    private var tvShowObserve = Observer<PagedList<TVShowItem>> {
        adapter.submitList(it)
        swiperefresh.isRefreshing = false
    }
}
