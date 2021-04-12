package com.feiyatsu.networking.data

sealed class NetworkResource<T> {
    data class Success<T>(val data: T) : NetworkResource<T>()
    data class Error<T>(val error: Throwable) : NetworkResource<T>()
    class Loading<T>(): NetworkResource<T>()
}