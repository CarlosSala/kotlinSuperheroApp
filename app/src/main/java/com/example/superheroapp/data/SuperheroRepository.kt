package com.example.superheroapp.data

import com.example.superheroapp.data.network.SuperheroService
import com.example.superheroapp.data.network.model.SuperherosDataResponseDto
import com.example.superheroapp.domain.model.SuperheroDetail
import com.example.superheroapp.domain.model.Superheros
import com.example.superheroapp.domain.model.toDomain
import com.example.superheroapp.domain.model.toDomainModel
import javax.inject.Inject

class SuperheroRepository @Inject constructor(
    private val api: SuperheroService
) {

    // private val api = SuperheroService()

    suspend fun getSuperheroFromApi(superhero: String): Superheros {
        val response: SuperherosDataResponseDto = api.getSuperheroes(superhero)
        return response.toDomainModel()
    }

    suspend fun getSuperheroDetailFromApi(superheroId: String): SuperheroDetail {
        val response = api.getSuperheroesDetail(superheroId)
        return response.toDomain()
    }
}