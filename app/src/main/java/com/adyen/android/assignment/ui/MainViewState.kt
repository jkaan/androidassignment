package com.adyen.android.assignment.ui

import android.os.Parcelable
import com.adyen.android.assignment.api.model.RecommendedItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainViewState(
    val header: String,
    val totalResults: Int,
    val recommendedItems: List<RecommendedItem>
) : Parcelable
