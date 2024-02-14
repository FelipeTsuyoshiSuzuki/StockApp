package com.example.stockapp.presentation.company_listing

sealed class CompanyListingEvents {
    object Refresh: CompanyListingEvents()
    data class OnSearchChange(val query: String): CompanyListingEvents()
}