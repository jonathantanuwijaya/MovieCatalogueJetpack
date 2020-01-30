package yovi.putra.moviecatalogue.ui.favorite.tvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_tvshow.*
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.core.common.Constant.IMAGE_URL
import yovi.putra.moviecatalogue.core.utils.ui.load
import yovi.putra.moviecatalogue.data.entity.TVShowItem

class TVShowFavoriteAdapter(private val listener: (TVShowItem) -> Unit)
    : PagedListAdapter<TVShowItem, TVShowFavoriteAdapter.VHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TVShowItem>() {
            override fun areItemsTheSame(oldConcert: TVShowItem, newConcert: TVShowItem) = oldConcert.id == newConcert.id
            override fun areContentsTheSame(oldConcert: TVShowItem, newConcert: TVShowItem) = oldConcert == newConcert
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder =
        VHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_tvshow, parent, false)
        )


    class VHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun binding(tvshow: TVShowItem, listener: (TVShowItem) -> Unit) {
            val context = containerView.context
            tv_title.text = tvshow.name
            tv_rating.text = tvshow.vote_average.toString()
            tv_vote.text = String.format(context.getString(R.string.vote_count), tvshow.vote_count)
            containerView.setOnClickListener { listener(tvshow) }
            img_poster.load(IMAGE_URL + tvshow.poster_path)
        }
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        getItem(position)?.let { holder.binding(it, listener)}
    }
}
