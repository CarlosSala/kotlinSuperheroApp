package com.example.superheroapp.domain

import com.example.superheroapp.data.SuperheroRepository
import com.example.superheroapp.data.network.model.SuperheroDetailResponseDto
import com.example.superheroapp.domain.model.Superheros

class GetSuperheroDetailUseCase {

    private val superheroRepository = SuperheroRepository()

    suspend operator fun invoke(superheroId: String): SuperheroDetailResponseDto {
        return superheroRepository.getSuperheroDetailFromApi(superheroId)
    }
}