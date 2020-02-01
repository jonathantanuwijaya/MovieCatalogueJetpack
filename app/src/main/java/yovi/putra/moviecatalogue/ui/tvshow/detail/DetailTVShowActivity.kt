package yovi.putra.moviecatalogue.ui.tvshow.detail

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
import yovi.putra.moviecatalogue.data.entity.TVShowDetailResponse
import yovi.putra.moviecatalogue.data.entity.TVShowItem

class DetailTVShowActivity : BaseToolbarActivity() {

    companion object {
        const val TVSHOW = "data"
        fun navigate(context: Context, tvShow: TVShowItem) {
            val intent = Intent(context, DetailTVShowActivity::class.java).apply {
                putExtra(TVSHOW, tvShow)
            }
            context.startActivity(intent)
        }
    }

    private val tvShowVM: DetailTVShowViewModel by viewModel()

    private lateinit var tvShowItem: TVShowItem

    private var isFavorited: Boolean = false

    override fun setupLayoutId(): Int = R.layout.activity_detail_movie

    override fun setButtonBack(): Boolean = true

    override fun setupData(savedInstanceState: Bundle?) {
        tvShowItem = intent.getParcelableExtra(TVSHOW) ?: TVShowItem()
        tvShowVM.getTVShow(tvShowItem.id)?.observe(this, tvShowDetailObserver)
        tvShowVM.loader.observe(this, loadingObserver)
        tvShowVM.isFavorited(tvShowItem.id)?.observe(this, isFavoriteObserver)
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setToolbar(R.id.toolbar)

        bt_favorite.setOnClickListener {
            setFavorite()
        }
    }

    private val tvShowDetailObserver = Observer<ResultState> {
        when (it) {
            is ResultState.Success<*> -> {
                when (it.data) { is TVShowDetailResponse -> { binding(it.data) } }
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

    private var isFavoriteObserver = Observer<TVShowItem?> {
        it?.let {  setButtonFavorite(true) } ?: run { setButtonFavorite(false) }
    }

    private fun binding(tvShow: TVShowDetailResponse?) {
        tvShow?.apply {
           toolbar_title.text = name
           tv_title.text = first_air_date
           tv_rating.text = vote_average.toString()
           tv_overview.text = overview

           img_poster.load(IMAGE_URL + poster_path)
           img_banner.load(IMAGE_URL + poster_path)
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
            tvShowVM.deleteFavorite(tvShowItem)
        } else {
            tvShowVM.addFavorite(tvShowItem)
        }
    }
}
