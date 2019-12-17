package yovi.putra.moviecatalogue.ui.tvshow

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.app_bar.*
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.base.BaseToolbarActivity
import yovi.putra.moviecatalogue.data.entity.TVShow

class DetailTVShowActivity : BaseToolbarActivity() {

    companion object {
        fun navigate(context: Context, idTvShow: Int) {
            val intent = Intent(context, DetailTVShowActivity::class.java).apply {
                putExtra("id", idTvShow)
            }
            context.startActivity(intent)
        }
    }

    private var tvShow: TVShow? = null

    override fun setupLayoutId(): Int = R.layout.activity_detail_movie

    override fun setButtonBack(): Boolean = true

    override fun setupData() {
        val id = intent.getIntExtra("id", -1)
        val vm = ViewModelProviders.of(this).get(TVShowViewModel::class.java)
        tvShow = vm.getTvShow(id)
    }

    override fun setupUI() {
        setToolbar(R.id.toolbar)
        tvShow?.apply {
            toolbar_title.text = title
            tv_title.text = first_air_date
            tv_rating.text = vote_average?.toString()
            tv_overview.text = overview

            Glide.with(this@DetailTVShowActivity)
                .load(poster_path)
                .placeholder(R.drawable.ic_placeholder)
                .into(img_poster)

            Glide.with(this@DetailTVShowActivity)
                .load(poster_path)
                .placeholder(R.drawable.ic_placeholder)
                .into(img_banner)
        }
    }
}
