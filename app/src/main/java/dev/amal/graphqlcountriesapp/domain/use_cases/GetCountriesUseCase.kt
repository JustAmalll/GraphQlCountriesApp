package dev.amal.graphqlcountriesapp.domain.use_cases

import dev.amal.graphqlcountriesapp.domain.CountryClient
import dev.amal.graphqlcountriesapp.domain.models.SimpleCountry

class GetCountriesUseCase(
    private val countryClient: CountryClient
) {
    suspend fun execute(): List<SimpleCountry> {
        return countryClient
            .getCountries()
            .sortedBy { it.name }
    }
}