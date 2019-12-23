package yovi.putra.moviecatalogue.ui.movie

import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_movie.*
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.core.base.BaseFragment

class MovieFm : BaseFragment() {

    private lateinit var adapter: MovieAdapter
    private lateinit var movieVM: MovieViewModel

    override fun setupLayoutId(): Int = R.layout.fragment_movie

    override fun setupData() {
        adapter = MovieAdapter { movie ->
            DetailMovieActivity.navigate(
                contextView,
                movie.id
            )
        }
        movieVM = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        adapter.setItem(movieVM.getMovie())
    }

    override fun setupUI() {
        swiperefresh.setColorSchemeColors(ContextCompat.getColor(contextView, R.color.colorAccent))
        swiperefresh.setOnRefreshListener {
            adapter.setItem(movieVM.getMovie())
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
