package com.example.superheroapp.ui.model

import com.example.superheroapp.data.network.model.SuperheroImageDto
import com.example.superheroapp.data.network.model.SuperheroItemDto
import com.example.superheroapp.data.network.model.SuperherosDataResponseDto
import com.example.superheroapp.domain.model.SuperheroImage
import com.example.superheroapp.domain.model.SuperheroItem
import com.example.superheroapp.domain.model.Superheros

data class SuperherosUI(
    val response: String,
    val results: List<SuperheroItemUI>
)

data class SuperheroItemUI(
    val superheroId: String,
    val name: String,
    val image: SuperheroImageUI
)

data class SuperheroImageUI(
    val url: String
)

// mappers
fun SuperheroItem.toUIModel(): SuperheroItemUI {
    return SuperheroItemUI(
        superheroId = this.superheroId,
        name = this.name,
        image = this.image.toUIModel()
    )
}

fun SuperheroImage.toUIModel(): SuperheroImageUI {
    return SuperheroImageUI(
        url = this.url
    )
}

fun Superheros.toUIModel(): SuperherosUI {
    return SuperherosUI(
        response = this.response,
        results = this.results.map { it.toUIModel() }
    )
}