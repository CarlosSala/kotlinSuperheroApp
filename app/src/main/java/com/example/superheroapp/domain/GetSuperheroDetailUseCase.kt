package com.example.superheroapp.domain

import com.example.superheroapp.data.SuperheroRepository
import com.example.superheroapp.domain.model.SuperheroDetail

class GetSuperheroDetailUseCase {

    private val superheroRepository = SuperheroRepository()

    suspend operator fun invoke(superheroId: String): SuperheroDetail {
        return superheroRepository.getSuperheroDetailFromApi(superheroId)
    }
}