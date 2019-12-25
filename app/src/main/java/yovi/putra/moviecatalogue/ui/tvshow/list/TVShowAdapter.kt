package yovi.putra.moviecatalogue.ui.tvshow.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.core.common.Constant.IMAGE_URL
import yovi.putra.moviecatalogue.core.utils.ui.load
import yovi.putra.moviecatalogue.data.entity.TVShowItem

class TVShowAdapter(private val listener: (TVShowItem) -> Unit)
    : RecyclerView.Adapter<TVShowAdapter.VHolder>() {

    private var item = mutableListOf<TVShowItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder =
        VHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        )

    fun setItem(data: List<TVShowItem>) {
        item.addAll(data)
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: VHolder, position: Int) =
        holder.binding(item[position], listener)

    class VHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun binding(tvshow: TVShowItem, listener: (TVShowItem) -> Unit){
            tv_title.text = tvshow.name
            tv_rating.text = tvshow.vote_average.toString()
            containerView.setOnClickListener { listener(tvshow) }
            img_poster.load(IMAGE_URL + tvshow.poster_path)
        }
    }
}
