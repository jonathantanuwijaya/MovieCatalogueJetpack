package yovi.putra.moviecatalogue.ui.movie

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.app_bar.*
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.base.BaseToolbarActivity
import yovi.putra.moviecatalogue.data.entity.Movie

class DetailMovieActivity : BaseToolbarActivity() {

    companion object {
        fun navigate(context: Context, id: Int) {
            val intent = Intent(context, DetailMovieActivity::class.java).apply {
                putExtra("id", id)
            }
            context.startActivity(intent)
        }
    }

    private var movie: Movie? = null

    override fun setupLayoutId(): Int = R.layout.activity_detail_movie

    override fun setButtonBack(): Boolean = true

    override fun setupData() {
        val id = intent.getIntExtra("id", -1)
        val vm = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movie = vm.getMovieById(id)
    }

    override fun setupUI() {
        setToolbar(R.id.toolbar)
        movie?.apply {
            toolbar_title.text = title
            tv_title.text = release_date
            tv_rating.text = vote_average
            tv_overview.text = overview

            Glide.with(this@DetailMovieActivity)
                .load(poster_path)
                .placeholder(R.drawable.ic_placeholder)
                .into(img_poster)
        }

    }
}
