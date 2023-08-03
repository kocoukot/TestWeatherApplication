package com.example.weatherapplication.domain.model

import com.example.weatherapplication.data.database.ForecastEntity
import com.google.gson.annotations.SerializedName

data class WeatherForecast(
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("main") val main: WeatherMain,
    @SerializedName("name") val name: String,
) {
    fun toDbEntity(placeName: String, zipcode: String) = ForecastEntity(
        place = placeName,
        temp = main.temp,
        feelsLike = main.feelsLike,
        tempMin = main.tempMin,
        tempMax = main.tempMax,
        weather = weather.first().main,
        description = weather.first().description,
        date = System.currentTimeMillis(),
        zipcode = zipcode
    )
}

class Weather(
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String
)


class WeatherMain(
    @SerializedName("temp") val temp: Float,
    @SerializedName("feels_like") val feelsLike: Float,
    @SerializedName("temp_min") val tempMin: Float,
    @SerializedName("temp_max") val tempMax: Float,
)