package yovi.putra.moviecatalogue.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.data.entity.Constant.IMAGE_URL
import yovi.putra.moviecatalogue.data.entity.Movie

class MovieAdapter(private val listener: (Movie) -> Unit)
    : RecyclerView.Adapter<MovieAdapter.VHolder>() {

    private var item = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder =
        VHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        )

    fun setItem(data: MutableList<Movie>) {
        item.addAll(data)
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: VHolder, position: Int) =
        holder.binding(item[position], listener)

    class VHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun binding(movie: Movie, listener: (Movie) -> Unit){
            tv_title.text = movie.title
            tv_rating.text = movie.vote_average
            containerView.setOnClickListener { listener(movie) }

            Glide.with(containerView.context)
                .load(IMAGE_URL + movie.poster_path)
                .placeholder(R.drawable.ic_placeholder)
                .into(img_poster)
        }
    }
}
