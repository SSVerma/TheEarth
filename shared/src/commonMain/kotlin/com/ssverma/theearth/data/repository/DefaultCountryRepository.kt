package com.ssverma.theearth.data.repository

import com.apollographql.apollo3.ApolloClient
import com.ssverma.theearth.CountryQuery
import com.ssverma.theearth.data.mapper.asCountries
import com.ssverma.theearth.data.mapper.asResult
import com.ssverma.theearth.data.remote.graphqlClient
import com.ssverma.theearth.domain.Result
import com.ssverma.theearth.domain.model.Country
import com.ssverma.theearth.domain.repository.CountryRepository

internal class DefaultCountryRepository constructor(
    private val networkClient: ApolloClient = graphqlClient
) : CountryRepository {

    override suspend fun fetchCountries(): Result<List<Country>, Unit> {
        return networkClient.query(CountryQuery()).asResult(
            mapData = { it.countries?.asCountries().orEmpty() },
            mapError = { Unit }
        )
    }
}