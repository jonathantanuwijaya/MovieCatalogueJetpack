package yovi.putra.moviecatalogue.ui.favorite.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.core.common.Constant.IMAGE_URL
import yovi.putra.moviecatalogue.core.utils.ui.load
import yovi.putra.moviecatalogue.data.entity.MovieItem

class MovieFavoriteAdapter(private val listener: (MovieItem) -> Unit)
    : PagedListAdapter<MovieItem, MovieFavoriteAdapter.VHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieItem>() {
            override fun areItemsTheSame(oldConcert: MovieItem, newConcert: MovieItem) = oldConcert.id == newConcert.id
            override fun areContentsTheSame(oldConcert: MovieItem, newConcert: MovieItem) = oldConcert == newConcert
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder =
        VHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        )

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        getItem(position)?.let { holder.binding(it, listener) }
    }

    class VHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun binding(movie: MovieItem, listener: (MovieItem) -> Unit) {
            val context = containerView.context
            tv_title.text = movie.title
            tv_rating.text = movie.vote_average.toString()
            tv_vote.text = String.format(context.getString(R.string.vote_count), movie.vote_count)
            tv_description.text = movie.overview
            img_poster.load(IMAGE_URL + movie.poster_path)
            containerView.setOnClickListener { listener(movie) }
        }
    }
}
