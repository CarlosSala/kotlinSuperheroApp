package com.example.superheroapp.ui.model

data class SuperheroDetailUI(
    val name: String,
    val powerStats: PowerStatsUI,
    val imageUrl: String,
    val biography: BiographyUI
)

data class PowerStatsUI(
    val intelligence: String,
    val strength: String,
    val speed: String,
    val durability: String,
    val power: String,
    val combat: String
)

data class SuperheroImageDetailUI(
    val url: String
)

data class BiographyUI(
    val fullName: String,
    val publisher: String
)
