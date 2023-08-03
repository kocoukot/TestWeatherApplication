package com.example.weatherapplication.data.repo

import com.example.weatherapplication.data.database.WeatherAppDao
import com.example.weatherapplication.data.network.BaseRepository
import com.example.weatherapplication.data.network.WeatherService
import com.example.weatherapplication.domain.WeatherAppRepository
import com.example.weatherapplication.domain.model.Resource
import com.example.weatherapplication.domain.model.WeatherForecast

class WeatherAppRepositoryImpl(
    private val weatherService: WeatherService,
    private val weatherDao: WeatherAppDao
) : WeatherAppRepository, BaseRepository() {

    override suspend fun getWeather(place: String, isTown: Boolean): Resource<WeatherForecast> {
        weatherDao.getRelevantForecast(place, System.currentTimeMillis())?.let {
            return Resource.Success(it.toWeatherForecast())
        }

        safeApiCall {
            if (isTown) {
                weatherService.getWeatherByTown(place)
            } else {
                weatherService.getWeatherByZip(place)
            }
        }.let { response ->
            if (response is Resource.Success) {
                weatherDao.insertOrUpdateForecast(
                    response.value.toDbEntity(
                        if (isTown) place else response.value.name,
                        if (isTown) "" else place
                    )
                )
            }
            return response
        }
    }
}