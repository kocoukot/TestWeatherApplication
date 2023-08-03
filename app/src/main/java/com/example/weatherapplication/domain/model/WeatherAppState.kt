package com.example.weatherapplication.domain.model

data class WeatherAppState(
    val isError: AlertType = AlertType.EmptyAlert,
    val isLoading: Boolean = false,
    val typedPlace: String = "",
    val isTown: Boolean = true,
    val forecast: WeatherForecast? = null,
    val currentScreen: WeatherAppScreens = WeatherAppScreens.PLACE_INPUT
)
