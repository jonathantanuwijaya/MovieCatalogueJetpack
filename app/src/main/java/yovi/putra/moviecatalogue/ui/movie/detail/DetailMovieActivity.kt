package yovi.putra.moviecatalogue.ui.movie.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.app_bar.*
import org.koin.android.viewmodel.ext.android.viewModel
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.core.base.BaseToolbarActivity
import yovi.putra.moviecatalogue.core.common.Constant.IMAGE_URL
import yovi.putra.moviecatalogue.core.utils.network.NetworkThrowable.errorMessage
import yovi.putra.moviecatalogue.core.utils.state.LoaderState
import yovi.putra.moviecatalogue.core.utils.state.ResultState
import yovi.putra.moviecatalogue.core.utils.ui.load
import yovi.putra.moviecatalogue.core.utils.ui.toast
import yovi.putra.moviecatalogue.data.entity.MovieDetailResponse
import yovi.putra.moviecatalogue.data.entity.MovieItem

class DetailMovieActivity : BaseToolbarActivity() {
    companion object {
        const val MOVIE = "movie"

        fun navigate(context: Context, movie: MovieItem) {
            val intent = Intent(context, DetailMovieActivity::class.java).apply {
                putExtra(MOVIE, movie)
            }
            context.startActivity(intent)
        }
    }

    private val movieVM: DetailMovieViewModel by viewModel()

    private lateinit var movieItem: MovieItem

    private var isFavorited: Boolean = false

    override fun setupLayoutId(): Int = R.layout.activity_detail_movie

    override fun setButtonBack(): Boolean = true

    override fun setupData(savedInstanceState: Bundle?) {
        movieItem = intent.getParcelableExtra(MOVIE) ?: MovieItem()
        movieVM.loader.observe(this, loadingObserver)
        movieVM.getMovie(movieItem.id)?.observe(this, movieDetailObserver)
        movieVM.isFavorited(movieItem.id)?.observe(this, isFavoritedObserver)
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setToolbar(R.id.toolbar)

        bt_favorite.setOnClickListener {
            setFavorite()
        }
    }

    private val movieDetailObserver = Observer<ResultState> {
        when (it) {
            is ResultState.Success<*> -> {
                when (it.data) { is MovieDetailResponse -> { binding(it.data) } }
            }
            is ResultState.Error -> {
                toast(errorMessage(this, it.error))
            }
        }
    }

    private var loadingObserver = Observer<LoaderState> {
        when (it) {
            is LoaderState.Show -> onShowLoader()
            is LoaderState.Hide -> onHideLoader()
        }
    }

    private val isFavoritedObserver = Observer<MovieItem?> {
        it?.let {
            setButtonFavorite(true)
        } ?: run {
            setButtonFavorite(false)
        }
    }

    private fun binding(movie: MovieDetailResponse?) {
        movie?.apply {
            toolbar_title.text = title
            tv_title.text = release_date
            tv_rating.text = vote_average.toString()
            tv_overview.text = overview
            img_poster.load(IMAGE_URL + poster_path)
            img_banner.load(IMAGE_URL + backdrop_path)
        }
    }

    private fun setButtonFavorite(isFavorited: Boolean) {
        this.isFavorited = isFavorited
        if (isFavorited)
            bt_favorite.setImageResource(R.drawable.ic_favorite_selected)
        else
            bt_favorite.setImageResource(R.drawable.ic_favorite_unselect)
    }

    private fun setFavorite() {
        if (isFavorited) {
            movieVM.deleteFavorite(movieItem)
        } else {
            movieVM.addFavorite(movieItem)
        }
    }
}
