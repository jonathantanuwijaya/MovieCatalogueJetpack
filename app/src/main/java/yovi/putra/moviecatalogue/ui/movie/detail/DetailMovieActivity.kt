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

class DetailMovieActivity : BaseToolbarActivity() {
    companion object {
        private const val MOVIE_ID = "id"

        fun navigate(context: Context, id: Int) {
            val intent = Intent(context, DetailMovieActivity::class.java).apply {
                putExtra(MOVIE_ID, id)
            }
            context.startActivity(intent)
        }
    }

    private val movieVM: DetailMovieViewModel by viewModel()

    override fun setupLayoutId(): Int = R.layout.activity_detail_movie

    override fun setButtonBack(): Boolean = true

    override fun setupData(savedInstanceState: Bundle?) {
        val id = intent.getIntExtra(MOVIE_ID, -1)
        movieVM.loader.observe(this, loadingObserver)
        movieVM.getMovie(id)?.observe(this, movieDetailObserver)
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setToolbar(R.id.toolbar)
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
}
