package com.ssverma.theearth.android

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ssverma.theearth.domain.model.Country

@Composable
fun CountryScreen(countryViewModel: CountryViewModel = viewModel()) {
    val countryState by countryViewModel.countryState.collectAsState()

    when (countryState) {
        CountryState.Failed -> {
            ErrorIndicator(message = "Something went wrong")
        }
        is CountryState.Loaded -> {
            CountryContent(countries = (countryState as CountryState.Loaded).countries)
        }
        CountryState.Loading -> {
            LoadingIndicator()
        }
    }
}

@Composable
private fun CountryContent(
    modifier: Modifier = Modifier,
    countries: List<Country>
) {
    LazyColumn(modifier.fillMaxSize()) {
        items(countries) { country ->
            CountryItem(country = country)
            Divider()
        }
    }
}

@Composable
private fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(modifier)
    }
}

@Composable
private fun ErrorIndicator(
    modifier: Modifier = Modifier,
    message: String
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = message)
    }
}

@Composable
private fun CountryItem(
    modifier: Modifier = Modifier,
    country: Country
) {
    Column(modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = country.flagEmoji, style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = country.name, style = MaterialTheme.typography.h4)
        }
        Text(text = country.native)
    }
}