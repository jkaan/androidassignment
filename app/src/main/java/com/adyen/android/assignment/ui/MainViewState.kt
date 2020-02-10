package com.adyen.android.assignment.ui

import android.os.Parcelable
import com.adyen.android.assignment.api.model.RecommendedItem
import kotlinx.android.parcel.Parcelize

sealed class MainViewState : Parcelable {
    @Parcelize
    data class Content(
        val header: String,
        val totalResults: Int,
        val recommendedItems: List<RecommendedItem>
    ) : MainViewState()

    @Parcelize
    data class Error(
        val errorMessage: String
    ) : MainViewState()
}
