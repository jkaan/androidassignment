package com.adyen.android.assignment.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    val address: String?,
    val cc: String?,
    val city: String?,
    val country: String?,
    val crossStreet: String?,
    val distance: Int?,
    val formattedAddress: List<String>?,
    val labeledLatLngs: List<LabeledLatLng>?,
    val lat: Double?,
    val lng: Double?,
    val postalCode: String?,
    val state: String?
) : Parcelable
