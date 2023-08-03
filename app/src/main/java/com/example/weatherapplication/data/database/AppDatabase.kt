package com.example.weatherapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [ForecastEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherForecast(): WeatherAppDao
}