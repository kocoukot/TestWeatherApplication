package com.example.weatherapplication.domain.model

import com.google.gson.annotations.SerializedName

data class WeatherForecast(
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("main") val main: WeatherMain
)

class Weather(
    @SerializedName("main") val main: String,
    @SerializedName("moderate rain") val weatherType: String
)


class WeatherMain(
    @SerializedName("temp") val temp: Float,
    @SerializedName("feels_like") val feelsLike: Float,
    @SerializedName("temp_min") val tempMin: Float,
    @SerializedName("temp_max") val tempMax: Float,
)