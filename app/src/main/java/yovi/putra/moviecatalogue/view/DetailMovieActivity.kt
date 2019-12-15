package yovi.putra.moviecatalogue.view

import android.content.Context
import android.content.Intent
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.app_bar.*
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.base.BaseToolbarActivity
import yovi.putra.moviecatalogue.model.Movie

class DetailMovieActivity : BaseToolbarActivity() {

    companion object {
        fun navigate(context: Context, movie: Movie) {
            val intent = Intent(context, DetailMovieActivity::class.java).apply {
                putExtra("movie", movie)
            }
            context.startActivity(intent)
        }
    }

    private lateinit var movie: Movie

    override fun setupLayoutId(): Int = R.layout.activity_detail_movie

    override fun setButtonBack(): Boolean = true

    override fun setupData() {
        movie = intent.getParcelableExtra("movie") as Movie
    }

    override fun setupUI() {
        toolbar_title.text = movie.title
        tv_title.text = movie.date
        tv_rating.text = movie.rating
        tv_overview.text = movie.description
    }
}
