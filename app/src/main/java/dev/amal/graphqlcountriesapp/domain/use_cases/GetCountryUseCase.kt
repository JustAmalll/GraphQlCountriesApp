package dev.amal.graphqlcountriesapp.domain.use_cases

import dev.amal.graphqlcountriesapp.domain.CountryClient
import dev.amal.graphqlcountriesapp.domain.models.DetailedCountry

class GetCountryUseCase(
    private val countryClient: CountryClient
) {
    suspend fun execute(code: String): DetailedCountry? {
        return countryClient.getCountry(code)
    }
}