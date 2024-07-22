package com.example.superheroapp.ui.model

import com.example.superheroapp.domain.model.Biography
import com.example.superheroapp.domain.model.PowerStats
import com.example.superheroapp.domain.model.SuperheroDetail

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

// mapper
fun Biography.toUIModel(): BiographyUI {
    return BiographyUI(
        fullName = this.fullName,
        publisher = this.publisher
    )
}

fun PowerStats.toUIModel(): PowerStatsUI {
    return PowerStatsUI(
        intelligence = this.intelligence,
        strength = this.strength,
        speed = this.speed,
        durability = this.durability,
        power = this.power,
        combat = this.combat
    )
}

fun SuperheroDetail.toUIModel(): SuperheroDetailUI {
    return SuperheroDetailUI(
        name = this.name,
        powerStats = this.powerStats.toUIModel(),
        imageUrl = this.image,
        biography = this.biography.toUIModel()
    )
}
