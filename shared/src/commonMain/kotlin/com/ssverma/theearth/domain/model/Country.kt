package com.ssverma.theearth.domain.model

data class Country(
    val code: String,
    val name: String,
    val flagEmoji: String,
    val native: String,
    val currency: String,
    val continent: Continent?
)

data class Continent(
    val code: String,
    val name: String
)