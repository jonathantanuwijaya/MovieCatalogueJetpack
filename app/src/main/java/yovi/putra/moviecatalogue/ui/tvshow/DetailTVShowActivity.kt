package yovi.putra.moviecatalogue.ui.tvshow

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.app_bar.*
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.core.base.BaseToolbarActivity
import yovi.putra.moviecatalogue.core.common.Constant.IMAGE_URL
import yovi.putra.moviecatalogue.core.utils.load
import yovi.putra.moviecatalogue.data.entity.TVShow

class DetailTVShowActivity : BaseToolbarActivity() {

    companion object {
        private const val MOVIE_ID = "id"
        fun navigate(context: Context, idTvShow: Int) {
            val intent = Intent(context, DetailTVShowActivity::class.java).apply {
                putExtra(MOVIE_ID, idTvShow)
            }
            context.startActivity(intent)
        }
    }

    private var tvShow: TVShow? = null

    override fun setupLayoutId(): Int = R.layout.activity_detail_movie

    override fun setButtonBack(): Boolean = true

    override fun setupData() {
        val id = intent.getIntExtra(MOVIE_ID, -1)
        val vm = ViewModelProviders.of(this).get(TVShowViewModel::class.java)
        tvShow = vm.getTvShow(id)
    }

    override fun setupUI() {
        setToolbar(R.id.toolbar)
        tvShow?.apply {
            toolbar_title.text = name
            tv_title.text = first_air_date
            tv_rating.text = vote_average?.toString()
            tv_overview.text = overview

            img_poster.load(IMAGE_URL + poster_path)
            img_banner.load(IMAGE_URL + poster_path)
        }
    }
}
