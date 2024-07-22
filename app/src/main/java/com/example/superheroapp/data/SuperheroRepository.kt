package com.example.superheroapp.data

import com.example.superheroapp.data.network.SuperheroService
import com.example.superheroapp.data.network.model.SuperheroDetailResponseDto
import com.example.superheroapp.data.network.model.SuperherosDataResponseDto
import com.example.superheroapp.domain.model.Superheros
import com.example.superheroapp.domain.model.toDomainModel

class SuperheroRepository {

    private val api = SuperheroService()

    suspend fun getSuperheroFromApi(superhero: String): Superheros {
        val response: SuperherosDataResponseDto = api.getSuperheroes(superhero)
        return response.toDomainModel()
    }

    suspend fun getSuperheroDetailFromApi(superheroId: String): SuperheroDetailResponseDto {
        return api.getSuperheroesDetail(superheroId)
    }
}