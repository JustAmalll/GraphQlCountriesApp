package dev.amal.graphqlcountriesapp.presentation

import dev.amal.graphqlcountriesapp.domain.models.DetailedCountry
import dev.amal.graphqlcountriesapp.domain.models.SimpleCountry

data class CountriesState(
    val countries: List<SimpleCountry> = emptyList(),
    val isLoading: Boolean = false,
    val selectedCountry: DetailedCountry? = null
)