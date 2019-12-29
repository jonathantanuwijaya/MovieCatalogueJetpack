package yovi.putra.moviecatalogue.ui.tvshow.list

import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_movie.*
import org.koin.android.viewmodel.ext.android.viewModel
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.core.base.BaseFragment
import yovi.putra.moviecatalogue.core.utils.network.NetworkThrowable.errorMessage
import yovi.putra.moviecatalogue.core.utils.state.LoaderState
import yovi.putra.moviecatalogue.core.utils.state.ResultState
import yovi.putra.moviecatalogue.core.utils.state.reObserver
import yovi.putra.moviecatalogue.core.utils.ui.toast
import yovi.putra.moviecatalogue.data.entity.TVShowListResponse
import yovi.putra.moviecatalogue.ui.tvshow.detail.DetailTVShowActivity

class TVShowFm : BaseFragment() {

    private lateinit var adapter: TVShowAdapter
    private val tvShowVM: TVShowViewModel by viewModel()

    override fun setupLayoutId(): Int = R.layout.fragment_movie

    override fun setupData() {
        adapter =
            TVShowAdapter { tvshow ->
                DetailTVShowActivity.navigate(
                    contextView,
                    tvshow.id
                )
            }

        tvShowVM.tvShow.reObserver(this, tvShowObserve)
        tvShowVM.loader.reObserver(this, loadingObserver)
    }

    private var tvShowObserve = Observer<ResultState> {
        when (it) {
            is ResultState.Success<*> -> {
                when (it.data) {
                    is TVShowListResponse -> { adapter.setItem(it.data.results) }
                }
            }
            is ResultState.Error -> {
                contextView.toast(errorMessage(contextView, it.error))
            }
        }
    }

    private var loadingObserver = Observer<LoaderState> {
        when (it) {
            is LoaderState.Show -> swiperefresh.isRefreshing = true
            is LoaderState.Hide -> swiperefresh.isRefreshing = false
        }
    }

    override fun setupUI() {
        swiperefresh.setColorSchemeColors(ContextCompat.getColor(contextView, R.color.colorAccent))
        swiperefresh.setOnRefreshListener {
           tvShowVM.reloadTVShow()
        }
        list_item.layoutManager = GridLayoutManager(contextView, 2)
        list_item.overScrollMode = View.OVER_SCROLL_NEVER
        list_item.adapter = adapter
    }
}
