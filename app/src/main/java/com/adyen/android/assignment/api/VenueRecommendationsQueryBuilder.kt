package com.adyen.android.assignment.api

class VenueRecommendationsQueryBuilder : PlacesQueryBuilder() {
    private var latitudeLongitude: String? = null
    private var near: String? = null

    fun setLatitudeLongitude(
        latitude: Double,
        longitude: Double
    ): VenueRecommendationsQueryBuilder {
        return this.apply {
            this.latitudeLongitude = "$latitude,$longitude"
        }
    }

    fun setNear(near: String): VenueRecommendationsQueryBuilder {
        return this.apply {
            this.near = near
        }
    }

    override fun putQueryParams(queryParams: MutableMap<String, String>) {
        latitudeLongitude?.apply { queryParams["ll"] = this }
        near?.apply { queryParams["near"] = this }
    }
}
