package com.adyen.android.assignment.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.adyen.android.assignment.CoroutinesTestRule
import com.adyen.android.assignment.api.PlacesRepository
import com.adyen.android.assignment.api.PlacesRepositoryImpl
import com.adyen.android.assignment.api.model.*
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `search - successful - returns venue recommendations`() {
        val recommendedItem = RecommendedItem(
            reasons = Reasons(
                count = 0, items = listOf(
                    Reasons.Item(
                        reasonName = "globalInteractionReason",
                        summary = "This spot is popular",
                        type = "general"
                    )
                )
            ),
            venue = Venue(
                categories = listOf(
                    Category(
                        icon = Icon(
                            prefix = "https://ss3.4sqi.net/img/categories_v2/parks_outdoors/park_",
                            suffix = ".png"
                        ),
                        id = "4bf58dd8d48988d163941735",
                        name = "Park",
                        pluralName = "Parks",
                        primary = true,
                        shortName = "Park"
                    )
                ),
                id = "4a27071df964a520f98c1fe3",
                location = Location(
                    address = "Vondelpark",
                    cc = "NL",
                    city = "Amsterdam",
                    country = "Nederland",
                    crossStreet = null,
                    distance = null,
                    formattedAddress = listOf(
                        "Vondelpark",
                        "Amsterdam",
                        "Nederland"
                    ),
                    labeledLatLngs = listOf(
                        LabeledLatLng(
                            label = "display",
                            lat = 52.35814715043192,
                            lng = 4.867072105407715
                        )
                    ),
                    lat = 52.35814715043192,
                    lng = 4.867072105407715,
                    postalCode = null,
                    state = "Noord-Holland"
                ),
                name = "Vondelpark",
                venuePage = null
            )
        )
        val response = VenueRecommendationsResponse(
            groups = listOf(
                VenueRecommendationGroup(
                    name = "Recommended places",
                    type = "places",
                    items = listOf(recommendedItem)
                )
            ),
            headerFullLocation = "Amsterdam",
            headerLocation = "Amsterdam",
            headerLocationGranularity = "city",
            suggestedBounds = SuggestedBounds(
                LatLng(
                    52.39027636075555,
                    4.960208535207601
                ),
                LatLng(
                    52.39027636075555,
                    4.960208535207601
                )
            ),
            suggestedRadius = 0,
            totalResults = 230,
            warning = null
        )
        val repo: PlacesRepository = mockk {
            coEvery { getVenueRecommendations("Amsterdam") } returns response
        }
        val viewModel = MainViewModel(repo)

        viewModel.handleIntent(Search("Amsterdam"))

        assert(
            viewModel.viewState.value == MainViewState.Content(
                header = "Amsterdam",
                totalResults = 230,
                recommendedItems = listOf(recommendedItem)
            )
        )
    }

    @Test
    fun `search - throws exception - returns venue recommendations`() {
        val repo: PlacesRepository = mockk {
            coEvery { getVenueRecommendations("Amsterdam") } throws PlacesRepositoryImpl.DataRetrievalException(
                "Timeout"
            )
        }
        val viewModel = MainViewModel(repo)

        viewModel.handleIntent(Search("Amsterdam"))

        assert(
            viewModel.viewState.value == MainViewState.Error(
                "Something went wrong, try again with a different search"
            )
        )
    }
}