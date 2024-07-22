package com.example.superheroapp.domain

import com.example.superheroapp.data.network.model.SuperherosDataResponseDto
import com.example.superheroapp.data.SuperheroRepository
import com.example.superheroapp.domain.model.Superheros

class SearchSuperheroUseCase {

    private val superheroRepository = SuperheroRepository()

    suspend operator fun invoke(superhero: String): Superheros {
        return superheroRepository.getSuperheroFromApi(superhero)
    }
}