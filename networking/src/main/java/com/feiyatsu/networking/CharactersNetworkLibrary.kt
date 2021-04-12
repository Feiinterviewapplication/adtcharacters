package com.feiyatsu.networking

import com.feiyatsu.networking.api.CharactersApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Boolean.FALSE

class CharactersNetworkLibrary {

    private val loggingInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .followRedirects(FALSE)
                    .addInterceptor(loggingInterceptor).build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.API_URL)
            .build()
    }

    val charactersApi: CharactersApi by lazy {
        retrofit.create(CharactersApi::class.java)
    }
}