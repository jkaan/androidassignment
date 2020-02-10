package com.adyen.android.assignment.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.adyen.android.assignment.api.model.RecommendedItem
import kotlinx.android.synthetic.main.view_holder_main.view.*

class MainViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bindTo(item: RecommendedItem) {
        item.venue.categories.getOrNull(0)?.icon?.let {
            view.view_venue_category_icon.load(it.prefix + "100" + it.suffix)
        }
        view.view_venue_name.text = item.venue.name
    }
}