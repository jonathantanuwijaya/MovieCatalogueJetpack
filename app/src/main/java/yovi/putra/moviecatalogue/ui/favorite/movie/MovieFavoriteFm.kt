package com.yeputra.moviecatalogue.view.favorite

import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_movie.*
import org.koin.android.viewmodel.ext.android.viewModel
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.core.base.BaseFragment
import yovi.putra.moviecatalogue.core.utils.network.NetworkThrowable.errorMessage
import yovi.putra.moviecatalogue.core.utils.state.LoaderState
import yovi.putra.moviecatalogue.core.utils.state.ResultState
import yovi.putra.moviecatalogue.core.utils.ui.toast
import yovi.putra.moviecatalogue.data.entity.MovieListResponse
import yovi.putra.moviecatalogue.ui.favorite.movie.MovieFavoriteViewModel
import yovi.putra.moviecatalogue.ui.movie.detail.DetailMovieActivity
import yovi.putra.moviecatalogue.ui.movie.list.MovieAdapter

class MovieFavoriteFm : BaseFragment() {

    private lateinit var adapter: MovieAdapter

    private val movieVM: MovieFavoriteViewModel by viewModel()

    override fun setupLayoutId(): Int = R.layout.fragment_movie

    override fun setupData() {
        adapter = MovieAdapter { movie ->
            DetailMovieActivity.navigate(
                contextView,
                movie.id
            )
        }
        movieVM.getMovie()?.observe(this, movieObserve)
        movieVM.loader.observe(this, loadingObserver)
    }

    override fun setupUI() {
        swiperefresh.setColorSchemeColors(ContextCompat.getColor(contextView, R.color.colorAccent))
        swiperefresh.setOnRefreshListener {
            movieVM.setMovie()
        }
        list_item.layoutManager = LinearLayoutManager(contextView)
        list_item.overScrollMode = View.OVER_SCROLL_NEVER
        list_item.adapter = adapter
    }

    private var movieObserve = Observer<ResultState> {
        when (it) {
            is ResultState.Success<*> -> {
                when (it.data) {
                    is MovieListResponse -> { adapter.setItem(it.data.results) }
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
}

