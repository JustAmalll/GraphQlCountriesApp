package dev.amal.graphqlcountriesapp.data

import dev.amal.CountriesQuery
import dev.amal.CountryQuery
import dev.amal.graphqlcountriesapp.domain.models.DetailedCountry
import dev.amal.graphqlcountriesapp.domain.models.SimpleCountry

fun CountryQuery.Country.toDetailedCountry(): DetailedCountry {
    return DetailedCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No capital",
        currency = currency ?: "No currency",
        languages = languages.mapNotNull { it.name },
        continent = continent.name
    )
}

fun CountriesQuery.Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No capital",
    )
}