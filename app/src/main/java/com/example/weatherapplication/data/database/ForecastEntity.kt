package com.example.weatherapplication.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.weatherapplication.data.database.ForecastTable.FORECAST_TABLE_DATE
import com.example.weatherapplication.data.database.ForecastTable.FORECAST_TABLE_FEELS_LIKE
import com.example.weatherapplication.data.database.ForecastTable.FORECAST_TABLE_NAME
import com.example.weatherapplication.data.database.ForecastTable.FORECAST_TABLE_PLACE
import com.example.weatherapplication.data.database.ForecastTable.FORECAST_TABLE_TEMP
import com.example.weatherapplication.data.database.ForecastTable.FORECAST_TABLE_TEMP_MAX
import com.example.weatherapplication.data.database.ForecastTable.FORECAST_TABLE_TEMP_MIN
import com.example.weatherapplication.data.database.ForecastTable.FORECAST_TABLE_WEATHER
import com.example.weatherapplication.data.database.ForecastTable.FORECAST_TABLE_WEATHER_DESCRIPTION
import com.example.weatherapplication.data.database.ForecastTable.FORECAST_TABLE_ZIPCODE
import com.example.weatherapplication.domain.model.Weather
import com.example.weatherapplication.domain.model.WeatherForecast
import com.example.weatherapplication.domain.model.WeatherMain


@Entity(
    tableName = FORECAST_TABLE_NAME,
    primaryKeys = [FORECAST_TABLE_PLACE, FORECAST_TABLE_ZIPCODE]
)
data class ForecastEntity(
    @ColumnInfo(name = FORECAST_TABLE_PLACE) val place: String,
    @ColumnInfo(name = FORECAST_TABLE_ZIPCODE) val zipcode: String = "",
    @ColumnInfo(name = FORECAST_TABLE_TEMP) val temp: Float,
    @ColumnInfo(name = FORECAST_TABLE_FEELS_LIKE) val feelsLike: Float,
    @ColumnInfo(name = FORECAST_TABLE_TEMP_MIN) val tempMin: Float,
    @ColumnInfo(name = FORECAST_TABLE_TEMP_MAX) val tempMax: Float,
    @ColumnInfo(name = FORECAST_TABLE_WEATHER) val weather: String,
    @ColumnInfo(name = FORECAST_TABLE_WEATHER_DESCRIPTION) val description: String,
    @ColumnInfo(name = FORECAST_TABLE_DATE) val date: Long,
) {

    fun toWeatherForecast() = WeatherForecast(
        weather = listOf(Weather(main = weather, description = description)), main = WeatherMain(
            temp = temp,
            feelsLike = feelsLike,
            tempMin = tempMin,
            tempMax = tempMax
        ), name = place
    )
}









