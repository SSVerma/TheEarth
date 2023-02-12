package com.ssverma.theearth.domain.usecase

import com.ssverma.theearth.data.repository.DefaultCountryRepository
import com.ssverma.theearth.domain.Result
import com.ssverma.theearth.domain.model.Country
import com.ssverma.theearth.domain.repository.CountryRepository

class LoadCountryUseCase(
    private val countryRepository: CountryRepository = DefaultCountryRepository()
) {

    companion object {
        fun create() = LoadCountryUseCase()
    }

    suspend operator fun invoke(): Result<List<Country>, Unit> {
        return countryRepository.fetchCountries()
    }
}
