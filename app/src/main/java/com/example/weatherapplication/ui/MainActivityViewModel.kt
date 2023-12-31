package com.example.weatherapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.domain.WeatherAppRepository
import com.example.weatherapplication.domain.model.AlertType
import com.example.weatherapplication.domain.model.Resource
import com.example.weatherapplication.domain.model.WeatherAppScreens
import com.example.weatherapplication.domain.model.WeatherAppState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val weatherAppRepository: WeatherAppRepository
) : ViewModel() {

    private val _state: MutableStateFlow<WeatherAppState> = MutableStateFlow(WeatherAppState())
    val state = _state.asStateFlow()

    fun getForecast(place: String, isTown: Boolean) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            when (val response = weatherAppRepository.getWeather(place, isTown)) {
                is Resource.Failure -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = AlertType.TextAlert(response.error.localizedMessage)
                        )
                    }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            forecast = response.value,
                            currentScreen = WeatherAppScreens.WEATHER_INFO,
                            typedPlace = place,
                            isTown = isTown
                        )
                    }
                }
            }
        }
    }

    fun closeError() {
        _state.update { it.copy(isError = AlertType.EmptyAlert) }
    }

    fun goBack() {
        _state.update {
            it.copy(
                forecast = null,
                currentScreen = WeatherAppScreens.PLACE_INPUT
            )
        }

    }

}