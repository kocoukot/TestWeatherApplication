package com.example.weatherapplication.domain

import com.example.weatherapplication.domain.model.Resource
import com.example.weatherapplication.domain.model.WeatherForecast

interface WeatherAppRepository {

    suspend fun getWeather(place: String): Resource<WeatherForecast>
}