package com.example.superheroapp.domain.model

import com.example.superheroapp.data.network.model.BiographyDto
import com.example.superheroapp.data.network.model.PowerStatsDto
import com.example.superheroapp.data.network.model.SuperheroDetailResponseDto
import com.example.superheroapp.data.network.model.SuperheroImageDetailDto

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

// mapper
fun BiographyDto.toDomain(): Biography {
    return Biography(
        fullName = this.fullName,
        publisher = this.publisher
    )
}

fun SuperheroImageDetailDto.toDomain(): SuperheroImageDetail {
    return SuperheroImageDetail(
        url = this.url
    )
}

fun PowerStatsDto.toDomain(): PowerStats {
    return PowerStats(
        intelligence = this.intelligence,
        strength = this.strength,
        speed = this.speed,
        durability = this.durability,
        power = this.power,
        combat = this.combat
    )
}

fun SuperheroDetailResponseDto.toDomain(): SuperheroDetail {
    return SuperheroDetail(
        name = this.name,
        powerStats = this.powerstats.toDomain(),
        image = this.image.url,
        biography = this.biographyDto.toDomain()
    )
}
