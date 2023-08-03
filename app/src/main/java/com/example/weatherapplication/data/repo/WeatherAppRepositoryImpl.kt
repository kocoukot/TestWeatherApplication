package com.example.weatherapplication.data.repo

import com.example.weatherapplication.data.network.BaseRepository
import com.example.weatherapplication.data.network.WeatherService
import com.example.weatherapplication.domain.WeatherAppRepository
import com.example.weatherapplication.domain.model.Resource
import com.example.weatherapplication.domain.model.WeatherForecast

class WeatherAppRepositoryImpl(
    private val weatherService: WeatherService
) : WeatherAppRepository, BaseRepository() {

    override suspend fun getWeather(place: String, isTown: Boolean): Resource<WeatherForecast> {
        safeApiCall {
            if (isTown) {
                weatherService.getWeatherByTown(place)
            } else {
                weatherService.getWeatherByZip(place)
            }
        }.let { response ->
            return response
        }
    }
}