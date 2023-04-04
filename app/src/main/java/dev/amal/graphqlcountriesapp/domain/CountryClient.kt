package dev.amal.graphqlcountriesapp.domain

import dev.amal.graphqlcountriesapp.domain.models.DetailedCountry
import dev.amal.graphqlcountriesapp.domain.models.SimpleCountry

interface CountryClient {
    suspend fun getCountries(): List<SimpleCountry>
    suspend fun getCountry(code: String): DetailedCountry?
}