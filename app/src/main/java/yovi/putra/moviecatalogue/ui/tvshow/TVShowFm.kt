package yovi.putra.moviecatalogue.ui.tvshow

import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_movie.*
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.base.BaseFragment
import yovi.putra.moviecatalogue.data.Movie
import yovi.putra.moviecatalogue.ui.detail.DetailMovieActivity
import yovi.putra.moviecatalogue.ui.movie.MovieAdapter
import yovi.putra.moviecatalogue.viewmodel.TVShowVM

class TVShowFm : BaseFragment() {

    private lateinit var adapter: MovieAdapter
    private lateinit var tvShowVM: TVShowVM
    private var tvShowObserver = Observer<MutableList<Movie>> {
        adapter.setItem(it)
        onHideLoader()
    }

    override fun setupLayoutId(): Int = R.layout.fragment_movie

    override fun setupData() {
        adapter = MovieAdapter { movie ->
            DetailMovieActivity.navigate(
                contextView,
                movie
            )
        }
        tvShowVM = ViewModelProviders.of(this).get(TVShowVM::class.java)
        tvShowVM.getTvShow().observe(this, tvShowObserver)
    }

    override fun setupUI() {
        swiperefresh.setColorSchemeColors(ContextCompat.getColor(contextView, R.color.colorAccent))
        swiperefresh.setOnRefreshListener {
            tvShowVM.getTvShow().observe(this, tvShowObserver)
        }
        list_item.layoutManager = GridLayoutManager(contextView, 2)
        list_item.overScrollMode = View.OVER_SCROLL_NEVER
        list_item.adapter = adapter
    }

    override fun onHideLoader() {
        swiperefresh.isRefreshing = false
    }
}
