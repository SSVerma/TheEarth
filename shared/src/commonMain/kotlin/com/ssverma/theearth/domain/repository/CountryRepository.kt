package com.ssverma.theearth.domain.repository

import com.ssverma.theearth.domain.Result
import com.ssverma.theearth.domain.model.Country

interface CountryRepository {

    suspend fun fetchCountries(): Result<List<Country>, Unit>
}