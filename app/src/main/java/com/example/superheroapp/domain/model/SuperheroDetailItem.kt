package com.example.superheroapp.domain.model

data class SuperheroDetail(
    val name: String,
    val powerStats: PowerStats,
    val image: String,
    val biography: Biography
)

data class PowerStats(
    val intelligence: String,
    val strength: String,
    val speed: String,
    val durability: String,
    val power: String,
    val combat: String
)

data class SuperheroImageDetail(
    val url: String
)

data class Biography(
    val fullName: String,
    val publisher: String
)