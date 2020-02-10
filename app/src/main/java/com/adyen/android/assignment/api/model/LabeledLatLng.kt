package com.adyen.android.assignment.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LabeledLatLng(
    val label: String,
    val lat: Double,
    val lng: Double
) : Parcelable
