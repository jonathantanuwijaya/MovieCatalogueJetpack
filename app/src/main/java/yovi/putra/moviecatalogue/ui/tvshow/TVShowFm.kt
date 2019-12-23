package yovi.putra.moviecatalogue.ui.tvshow

import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_movie.*
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.core.base.BaseFragment

class TVShowFm : BaseFragment() {

    private lateinit var adapter: TVShowAdapter
    private lateinit var tvShowVM: TVShowViewModel

    override fun setupLayoutId(): Int = R.layout.fragment_movie

    override fun setupData() {
        adapter = TVShowAdapter { tvshow ->
            DetailTVShowActivity.navigate(
                contextView,
                tvshow.id
            )
        }
        tvShowVM = ViewModelProviders.of(this).get(TVShowViewModel::class.java)
        adapter.setItem(tvShowVM.getTvShow())
    }

    override fun setupUI() {
        swiperefresh.setColorSchemeColors(ContextCompat.getColor(contextView, R.color.colorAccent))
        swiperefresh.setOnRefreshListener {
            adapter.setItem(tvShowVM.getTvShow())
            onHideLoader()
        }
        list_item.layoutManager = GridLayoutManager(contextView, 2)
        list_item.overScrollMode = View.OVER_SCROLL_NEVER
        list_item.adapter = adapter
    }

    override fun onHideLoader() {
        swiperefresh.isRefreshing = false
    }
}
