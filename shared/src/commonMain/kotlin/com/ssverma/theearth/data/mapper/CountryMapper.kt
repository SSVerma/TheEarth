package com.ssverma.theearth.data.mapper

import com.ssverma.theearth.CountryQuery
import com.ssverma.theearth.domain.model.Continent
import com.ssverma.theearth.domain.model.Country

fun CountryQuery.Country.asCountry(): Country {
    return Country(
        code = code.orEmpty(),
        name = name.orEmpty(),
        flagEmoji = emoji.orEmpty(),
        native = native.orEmpty(),
        currency = currency.orEmpty(),
        continent = continent?.asContinent()
    )
}

fun CountryQuery.Continent.asContinent(): Continent {
    return Continent(
        code = code.orEmpty(),
        name = name.orEmpty()
    )
}

fun List<CountryQuery.Country?>.asCountries(): List<Country> {
    return this.mapNotNull { it?.asCountry() }
}