package com.adyen.android.assignment.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecommendedItem(
    val reasons: Reasons,
    val venue: Venue
) : Parcelable