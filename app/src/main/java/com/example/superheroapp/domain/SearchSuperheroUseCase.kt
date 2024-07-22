package com.example.superheroapp.domain

import com.example.superheroapp.data.SuperheroRepository
import com.example.superheroapp.domain.model.Superheros
import javax.inject.Inject


class SearchSuperheroUseCase @Inject constructor(
    private val superheroRepository: SuperheroRepository
) {
   // private val superheroRepository = SuperheroRepository()

    suspend operator fun invoke(superhero: String): Superheros {
        return superheroRepository.getSuperheroFromApi(superhero)
    }
}