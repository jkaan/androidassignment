package com.adyen.android.assignment.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Reasons(
    val count: Int,
    val items: List<Item>
) : Parcelable {
    @Parcelize
    data class Item(
        val reasonName: String,
        val summary: String,
        val type: String
    ) : Parcelable
}
