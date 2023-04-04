package dev.amal.graphqlcountriesapp.data

import com.apollographql.apollo3.ApolloClient
import dev.amal.CountriesQuery
import dev.amal.CountryQuery
import dev.amal.graphqlcountriesapp.domain.CountryClient
import dev.amal.graphqlcountriesapp.domain.models.DetailedCountry
import dev.amal.graphqlcountriesapp.domain.models.SimpleCountry

class ApolloCountryClient(
    private val apolloClient: ApolloClient
) : CountryClient {

    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toSimpleCountry() }
            ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailedCountry()
    }
}