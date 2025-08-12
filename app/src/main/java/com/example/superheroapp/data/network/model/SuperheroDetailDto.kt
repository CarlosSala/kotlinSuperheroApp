package com.example.superheroapp.data.network.model

import com.google.gson.annotations.SerializedName

data class SuperheroDetailResponseDto(
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerstats: PowerStatsDto,
    @SerializedName("image") val image: SuperheroImageDetailDto,
    @SerializedName("biography") val biographyDto: BiographyDto
)

data class PowerStatsDto(
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("combat") val combat: String
)

data class SuperheroImageDetailDto(
    @SerializedName("url") val url: String
)

data class BiographyDto(
    @SerializedName("full-name") val fullName: String,
    @SerializedName("publisher") val publisher: String
)