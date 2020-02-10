package com.adyen.android.assignment.api

import com.adyen.android.assignment.api.model.VenueRecommendationsResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface PlacesRepository {
    suspend fun getVenueRecommendations(near: String): VenueRecommendationsResponse
}

class PlacesRepositoryImpl(
    private val service: PlacesService = PlacesService.instance,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PlacesRepository {
    override suspend fun getVenueRecommendations(near: String): VenueRecommendationsResponse {
        val queryBuilder = VenueRecommendationsQueryBuilder()
            .setNear(near)
            .build()

        return try {
            withContext(dispatcher) { service.getVenueRecommendations(queryBuilder).response }
        } catch (exception: Throwable) {
            throw DataRetrievalException(exception.message)
        }
    }

    class DataRetrievalException(message: String?) : Throwable()
}