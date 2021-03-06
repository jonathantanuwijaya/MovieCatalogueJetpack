package yovi.putra.moviecatalogue.ui.movie.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.core.common.Constant.IMAGE_URL
import yovi.putra.moviecatalogue.core.utils.ui.load
import yovi.putra.moviecatalogue.data.entity.MovieItem

class MovieAdapter(private val listener: (MovieItem) -> Unit)
    : RecyclerView.Adapter<MovieAdapter.VHolder>() {

    private var item = mutableListOf<MovieItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder =
        VHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        )

    fun setItem(data: List<MovieItem>?) {
        data?.let {
            item.addAll(it)
            this.notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: VHolder, position: Int) =
        holder.binding(item[position], listener)

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
