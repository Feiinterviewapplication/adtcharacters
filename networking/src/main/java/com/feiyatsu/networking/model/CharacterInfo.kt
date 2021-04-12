package com.feiyatsu.networking.model

import com.google.gson.annotations.SerializedName

data class CharacterInfo(
    val count: Int,
    val pages: Int,
    @SerializedName("next")
    val nextUrl: String? = null,
    @SerializedName("prev")
    val prevUrl: String? = null
)
