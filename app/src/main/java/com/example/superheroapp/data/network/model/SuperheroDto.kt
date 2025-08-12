package com.example.superheroapp.data.network.model

import com.google.gson.annotations.SerializedName

data class SuperherosDataResponseDto(
    @SerializedName("response") val response: String,
    @SerializedName("results") val results: List<SuperheroItemDto>
)

data class SuperheroItemDto(
    @SerializedName("id") val superheroId: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: SuperheroImageDto
)

data class SuperheroImageDto(
    @SerializedName("url") val url: String,
)