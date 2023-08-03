package com.example.weatherapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Dialog
import com.example.weatherapplication.common.theme.WeatherApplicationTheme
import com.example.weatherapplication.domain.model.AlertType
import com.example.weatherapplication.domain.model.WeatherAppScreens
import com.example.weatherapplication.ui.InputScreen
import com.example.weatherapplication.ui.MainActivityViewModel
import com.example.weatherapplication.ui.WeatherForecastScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModel()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val state by viewModel.state.collectAsState()
            if (state.isLoading) {
                Dialog(onDismissRequest = { }) {
                    CircularProgressIndicator()
                }
            }
            WeatherApplicationTheme {
                if (state.isError !is AlertType.EmptyAlert) {
                    state.isError.ShowError {
                        viewModel.closeError()
                    }
                }

                Scaffold(
                    topBar = {
                        if (state.currentScreen != WeatherAppScreens.PLACE_INPUT)
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                IconButton(onClick = { viewModel.goBack() }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.arrow_back),
                                        contentDescription = "back button"
                                    )
                                }
                            }
                    },
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Crossfade(targetState = state.currentScreen, label = "") { screen ->
                        when (screen) {
                            WeatherAppScreens.PLACE_INPUT -> InputScreen(
                                state.typedPlace,
                                state.isTown
                            ) { place, isTown ->
                                viewModel.getForecast(place.trim(), isTown)
                            }

                            WeatherAppScreens.WEATHER_INFO -> {
                                state.forecast?.let {
                                    WeatherForecastScreen(
                                        if (state.isTown) state.typedPlace else state.forecast?.name.orEmpty(),
                                        it
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}