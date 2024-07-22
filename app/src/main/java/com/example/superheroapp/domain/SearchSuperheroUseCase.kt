package com.example.superheroapp.domain

import com.example.superheroapp.data.network.SuperheroDataResponse
import com.example.superheroapp.data.network.SuperheroRepository

class SearchSuperheroUseCase {

    private val superheroRepository = SuperheroRepository()

    suspend operator fun invoke(superhero: String): SuperheroDataResponse {
        return superheroRepository.getSuperheroFromApi(superhero)
    }
}