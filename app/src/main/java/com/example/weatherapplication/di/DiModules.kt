package com.example.weatherapplication.di

import androidx.room.Room
import com.example.weatherapplication.BuildConfig
import com.example.weatherapplication.data.database.AppDatabase
import com.example.weatherapplication.data.network.WeatherService
import com.example.weatherapplication.data.repo.WeatherAppRepositoryImpl
import com.example.weatherapplication.domain.WeatherAppRepository
import com.example.weatherapplication.ui.MainActivityViewModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.ParametersHolder
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val API_CONNECT_TIMEOUT = 15L
private const val API_READ_WRITE_TIMEOUT = 30L

val networkModule = module {

    single { Room.databaseBuilder(get(), AppDatabase::class.java, "room_forecast.db").build() }

    single { get<AppDatabase>().weatherForecast() }


    single {
        GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .create()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .connectTimeout(API_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(API_READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(API_READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    factory { parameters: ParametersHolder ->
        Retrofit.Builder()
            .client(get())
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
//
//    single { ServiceErrorHandler(get()) }
//

    single {
        get<Retrofit>()
            .create(WeatherService::class.java)
    }

    factory<WeatherAppRepository> { WeatherAppRepositoryImpl(get(), get()) }

    viewModel { MainActivityViewModel(get()) }

}