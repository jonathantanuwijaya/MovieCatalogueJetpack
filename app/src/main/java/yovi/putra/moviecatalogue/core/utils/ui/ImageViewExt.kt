package yovi.putra.moviecatalogue.core.utils.ui

import android.widget.ImageView
import com.bumptech.glide.Glide
import yovi.putra.moviecatalogue.R

/***
 * Image View with Glide
 */
fun ImageView.load(path: String, placeholder: Int = R.drawable.ic_placeholder) {
    Glide.with(this)
        .load(path)
        .placeholder(placeholder)
        .into(this)
}