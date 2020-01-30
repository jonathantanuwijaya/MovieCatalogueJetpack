package com.yeputra.moviecatalogue.view.favorite

import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_movie.*
import org.koin.android.viewmodel.ext.android.viewModel
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.core.base.BaseFragment
import yovi.putra.moviecatalogue.data.entity.MovieItem
import yovi.putra.moviecatalogue.ui.favorite.movie.MovieFavoriteAdapter
import yovi.putra.moviecatalogue.ui.favorite.movie.MovieFavoriteViewModel
import yovi.putra.moviecatalogue.ui.movie.detail.DetailMovieActivity

class MovieFavoriteFm : BaseFragment() {

    private lateinit var adapter: MovieFavoriteAdapter

    private val movieVM: MovieFavoriteViewModel by viewModel()

    override fun setupLayoutId(): Int = R.layout.fragment_movie

    override fun setupData() {
        adapter = MovieFavoriteAdapter { movie ->
            DetailMovieActivity.navigate(
                contextView,
                movie
            )
        }
        movieVM.getMovie()?.observe(this, movieObserve)
    }

    override fun setupUI() {
        swiperefresh.setColorSchemeColors(ContextCompat.getColor(contextView, R.color.colorAccent))
        swiperefresh.setOnRefreshListener {
            movieVM.getMovie()?.observe(this, movieObserve)
        }
        list_item.layoutManager = LinearLayoutManager(contextView)
        list_item.overScrollMode = View.OVER_SCROLL_NEVER
        list_item.adapter = adapter
    }

    private var movieObserve = Observer<PagedList<MovieItem>> {
        adapter.submitList(it)
    }
}

