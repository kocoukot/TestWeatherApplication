package com.example.weatherapplication.data.network

import com.example.weatherapplication.BuildConfig
import com.example.weatherapplication.domain.model.WeatherForecast
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {

    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = BuildConfig.API_KEY,
        @Query("units") units: String = "metric"
    ): WeatherForecast


}