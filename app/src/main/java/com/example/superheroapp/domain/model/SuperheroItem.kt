package com.example.superheroapp.domain.model

import com.example.superheroapp.data.network.model.SuperheroImageDto
import com.example.superheroapp.data.network.model.SuperheroItemDto
import com.example.superheroapp.data.network.model.SuperherosDataResponseDto

data class Superheros(
    val response: String,
    val results: List<SuperheroItem>
)

data class SuperheroItem(
    val superheroId: String,
    val name: String,
    val image: SuperheroImage
)

data class SuperheroImage(
    val url: String,
)

// mappers
fun SuperheroItemDto.toDomainModel(): SuperheroItem {
    return SuperheroItem(
        superheroId = this.superheroId,
        name = this.name,
        image = this.image.toDomainModel()
    )
}

fun SuperheroImageDto.toDomainModel(): SuperheroImage {
    return SuperheroImage(
        url = this.url
    )
}

fun SuperherosDataResponseDto.toDomainModel(): Superheros {
    return Superheros(
        response = this.response,
        results = this.results.map { it.toDomainModel() }
    )
}