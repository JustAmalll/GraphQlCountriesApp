package dev.amal.graphqlcountriesapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.amal.graphqlcountriesapp.domain.use_cases.GetCountriesUseCase
import dev.amal.graphqlcountriesapp.domain.use_cases.GetCountryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getCountryUseCase: GetCountryUseCase
): ViewModel() {

    private val _state = MutableStateFlow(CountriesState())
    val state = _state.asStateFlow()

    init {
        getCountries()
    }

    private fun getCountries() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val countries = getCountriesUseCase.execute()
            _state.update { it.copy(countries = countries, isLoading = false) }
        }
    }

    fun selectCountry(code: String) {
        viewModelScope.launch {
            _state.update { it.copy(selectedCountry = getCountryUseCase.execute(code)) }
        }
    }

    fun dismissCountryDialog() {
        _state.update { it.copy(selectedCountry = null) }
    }
}