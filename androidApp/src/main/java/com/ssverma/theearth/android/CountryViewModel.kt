package com.ssverma.theearth.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssverma.theearth.domain.Result
import com.ssverma.theearth.domain.model.Country
import com.ssverma.theearth.domain.usecase.LoadCountryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface CountryState {
    object Loading : CountryState

    data class Loaded(
        val countries: List<Country>
    ) : CountryState

    object Failed : CountryState
}

class CountryViewModel constructor(
    private val countryUseCase: LoadCountryUseCase = LoadCountryUseCase()
) : ViewModel() {

    private val _countryState = MutableStateFlow<CountryState>(CountryState.Loading)
    val countryState = _countryState.asStateFlow()

    init {
        viewModelScope.launch {
            when (val countryResult = countryUseCase()) {
                is Result.Error -> {
                    _countryState.update { CountryState.Failed }
                }
                is Result.Success -> {
                    _countryState.update { CountryState.Loaded(countries = countryResult.data) }
                }
            }
        }
    }
}