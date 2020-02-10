package com.adyen.android.assignment.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Venue(
    val categories: List<Category>,
    val id: String,
    val location: Location,
    val name: String,
    val venuePage: VenuePage?
) : Parcelable
