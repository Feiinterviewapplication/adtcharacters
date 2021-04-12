package com.feiyatsu.networking.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("info")
    val characterInfo: CharacterInfo,
    @SerializedName("results")
    val characters: List<Character>
)
