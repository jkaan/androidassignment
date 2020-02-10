package com.adyen.android.assignment.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adyen.android.assignment.api.PlacesRepository
import com.adyen.android.assignment.api.PlacesRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: PlacesRepository = PlacesRepositoryImpl()
) : ViewModel() {
    private val _viewState = MutableLiveData<MainViewState>()
    val viewState: LiveData<MainViewState>
        get() = _viewState

    fun handleIntent(intent: MainIntent) {
        when (intent) {
            is Search -> {
                viewModelScope.launch {
                    try {
                        val venueRecommendations = repository.getVenueRecommendations(intent.text)
                        _viewState.value = MainViewState.Content(
                            header = venueRecommendations.headerFullLocation,
                            totalResults = venueRecommendations.totalResults,
                            recommendedItems = venueRecommendations.groups.getOrNull(0)?.items
                                ?: emptyList()
                        )
                    } catch (exception: Throwable) {
                        _viewState.value = MainViewState.Error(
                            errorMessage = "Something went wrong, try again with a different search"
                        )
                    }
                }
            }
        }
    }
}
