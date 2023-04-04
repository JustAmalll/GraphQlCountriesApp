package dev.amal.graphqlcountriesapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import dev.amal.graphqlcountriesapp.domain.models.DetailedCountry
import dev.amal.graphqlcountriesapp.domain.models.SimpleCountry

@Composable
fun CountriesScreen(
    state: CountriesState,
    onSelectCountry: (code: String) -> Unit,
    onDismissCountryDialog: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
        else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.countries) { country ->
                    CountryItem(country = country, onSelectCountry = onSelectCountry)
                }
            }

            if (state.selectedCountry != null) CountryDialog(
                country = state.selectedCountry,
                onDismiss = onDismissCountryDialog
            )
        }
    }
}

@Composable
private fun CountryDialog(
    country: DetailedCountry,
    onDismiss: () -> Unit
) {
    val joinedLanguages = remember(country.languages) {
        country.languages.joinToString()
    }
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(Color.White)
                .padding(16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = country.emoji, fontSize = 30.sp)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = country.name, fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Continent: " + country.continent)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Currency: " + country.currency)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Capital: " + country.capital)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Language(s): $joinedLanguages")
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun CountryItem(
    country: SimpleCountry,
    onSelectCountry: (code: String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelectCountry(country.code) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = country.emoji, fontSize = 30.sp)
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = country.name, fontSize = 24.sp)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = country.capital)
        }
    }
}