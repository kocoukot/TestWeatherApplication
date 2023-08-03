package com.example.weatherapplication.domain.model

data class WeatherAppState(
    val typedPlace: String,
    val currentScreen: WeatherAppScreens
)
