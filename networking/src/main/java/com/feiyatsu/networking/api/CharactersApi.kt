package com.feiyatsu.networking.api

import com.feiyatsu.networking.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApi {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int? = null
    ): CharacterResponse
}