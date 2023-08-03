package com.example.weatherapplication.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapplication.data.database.ForecastTable.FORECAST_TABLE_DATE
import com.example.weatherapplication.data.database.ForecastTable.FORECAST_TABLE_NAME
import com.example.weatherapplication.data.database.ForecastTable.FORECAST_TABLE_PLACE
import com.example.weatherapplication.data.database.ForecastTable.FORECAST_TABLE_ZIPCODE


@Dao
interface WeatherAppDao {

    @Query("SELECT * FROM $FORECAST_TABLE_NAME WHERE ($FORECAST_TABLE_PLACE LIKE :placeName OR $FORECAST_TABLE_ZIPCODE LIKE :placeName) AND :currentTime - $FORECAST_TABLE_DATE  < 60000 LIMIT 1")
    suspend fun getRelevantForecast(placeName: String, currentTime: Long): ForecastEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateForecast(forecast: ForecastEntity)

}