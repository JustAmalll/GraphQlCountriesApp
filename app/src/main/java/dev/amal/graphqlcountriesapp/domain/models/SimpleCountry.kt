package dev.amal.graphqlcountriesapp.domain.models

data class SimpleCountry(
    val code: String,
    val name: String,
    val emoji: String,
    val capital: String
)