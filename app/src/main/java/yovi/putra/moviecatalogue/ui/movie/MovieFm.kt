package yovi.putra.moviecatalogue.ui.movie

import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_movie.*
import yovi.putra.moviecatalogue.viewmodel.MovieVM
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.base.BaseFragment
import yovi.putra.moviecatalogue.data.Movie
import yovi.putra.moviecatalogue.ui.detail.DetailMovieActivity

class MovieFm : BaseFragment() {

    private lateinit var adapter: MovieAdapter
    private lateinit var movieVM: MovieVM
    private var movieObserver = Observer<MutableList<Movie>> {
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
        movieVM = ViewModelProviders.of(this).get(MovieVM::class.java)
        movieVM.getMovie().observe(this, movieObserver)
    }

    override fun setupUI() {
        swiperefresh.setColorSchemeColors(ContextCompat.getColor(contextView, R.color.colorAccent))
        swiperefresh.setOnRefreshListener {
            movieVM.getMovie().observe(this, movieObserver)
        }
        list_item.layoutManager = GridLayoutManager(contextView, 2)
        list_item.overScrollMode = View.OVER_SCROLL_NEVER
        list_item.adapter = adapter
    }

    override fun onHideLoader() {
        swiperefresh.isRefreshing = false
    }
}
