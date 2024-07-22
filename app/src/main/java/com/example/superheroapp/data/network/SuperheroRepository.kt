package com.example.superheroapp.data.network

class SuperheroRepository {

    private val api = SuperheroService()

    suspend fun getSuperheroFromApi(superhero: String): SuperheroDataResponse {
        return api.getSuperheroes(superhero)
    }
}