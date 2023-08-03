package com.example.weatherapplication.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapplication.R
import com.example.weatherapplication.domain.model.WeatherForecast

@Composable
fun WeatherForecastScreen(place: String, forecast: WeatherForecast) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            place.replaceFirstChar { it.uppercase() },
            fontSize = 24.sp
        )
        Text(
            stringResource(
                id = R.string.weather_type,
                "${forecast.weather.first().main} / ${forecast.weather.first().description}"
            )
        )
        Text(
            stringResource(
                id = R.string.temp_values,
                forecast.main.temp.toString(),
                forecast.main.feelsLike.toString()
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            stringResource(
                id = R.string.temp_min_max,
                forecast.main.tempMin.toString(),
                forecast.main.tempMax.toString()
            )
        )

    }
}
