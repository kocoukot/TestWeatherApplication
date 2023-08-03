package com.example.weatherapplication.domain.model

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(val error: Throwable) : Resource<Nothing>()
}