package com.feiyatsu.networking.model

import com.google.gson.annotations.SerializedName

data class Character(
    val id: Long,
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val type: String = "",
    val gender: String = "",
    @SerializedName("origin")
    val origin: BaseInfo,
    @SerializedName("location")
    val location: BaseInfo,
    val image: String = "",
    val episodes: List<String>,
    val url: String = "",
    val created: String = ""
)
