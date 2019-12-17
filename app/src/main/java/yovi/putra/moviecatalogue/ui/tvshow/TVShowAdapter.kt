package yovi.putra.moviecatalogue.ui.tvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.data.entity.TVShow

class TVShowAdapter(private val listener: (TVShow) -> Unit)
    : RecyclerView.Adapter<TVShowAdapter.VHolder>() {

    private var item = mutableListOf<TVShow>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder =
        VHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        )

    fun setItem(data: MutableList<TVShow>) {
        item.addAll(data)
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: VHolder, position: Int) =
        holder.binding(item[position], listener)

    class VHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun binding(tvshow: TVShow, listener: (TVShow) -> Unit){
            tv_title.text = tvshow.name
            tv_rating.text = tvshow.vote_average?.toString()
            containerView.setOnClickListener { listener(tvshow) }

            Glide.with(containerView.context)
                .load(tvshow.poster_path)
                .placeholder(R.drawable.ic_placeholder)
                .into(img_poster)
        }
    }
}
