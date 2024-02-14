package com.example.stockapp.presentation.company_listing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockapp.domain.repository.StockRepository
import com.example.stockapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyLIstingViewModel @Inject constructor(
    private val repository: StockRepository
): ViewModel() {

    var state by mutableStateOf(CompanyListingStates())
    private var searchJob: Job? = null

    fun onEvent(event: CompanyListingEvents) {
        when(event) {
            is CompanyListingEvents.OnSearchChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getCompanyListing()
                }
            }
            is CompanyListingEvents.Refresh -> {
                getCompanyListing(fetchFromRemote = true)
            }
        }
    }

    fun getCompanyListing(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository
                .getCompanyListing(fetchFromRemote, query)
                .collect { result ->
                    when(result) {
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                        is Resource.Success -> {
                            result.data?.let { listing ->
                                state = state.copy(
                                    companies = listing
                                )
                            }
                        }
                    }
                }
        }
    }
}