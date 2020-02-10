package com.adyen.android.assignment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.adyen.android.assignment.R
import com.adyen.android.assignment.api.model.RecommendedItem

class MainListAdapter :
    androidx.recyclerview.widget.ListAdapter<RecommendedItem, MainViewHolder>(
        RecommendedItemDiffCallback
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_main, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        getItem(position)?.let { holder.bindTo(it) }
    }
}

object RecommendedItemDiffCallback : DiffUtil.ItemCallback<RecommendedItem>() {
    override fun areItemsTheSame(
        oldItem: RecommendedItem,
        newItem: RecommendedItem
    ) = oldItem.venue == oldItem.venue

    override fun areContentsTheSame(
        oldItem: RecommendedItem,
        newItem: RecommendedItem
    ) = oldItem == newItem
}