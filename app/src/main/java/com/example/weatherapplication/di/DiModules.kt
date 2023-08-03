package com.example.weatherapplication.di

import com.example.weatherapplication.BuildConfig
import com.example.weatherapplication.data.network.WeatherService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.parameter.ParametersHolder
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

private const val API_CONNECT_TIMEOUT = 15L
private const val API_READ_WRITE_TIMEOUT = 30L

val networkModule = module {

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
        val path = parameters.getOrNull<String>()
        Retrofit.Builder()
            .client(get())
            .baseUrl(BuildConfig.BASE_URL)
//            .addConverterFactory(RetrofitConverterFactory(get()))
            .build()
    }
//
//    single { ServiceErrorHandler(get()) }
//

    single {
        get<Retrofit>()
            .create(WeatherService::class.java)
    }

}