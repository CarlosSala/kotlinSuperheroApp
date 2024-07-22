package com.example.superheroapp.data.network

import com.example.superheroapp.data.network.model.SuperheroDetailResponseDto
import com.example.superheroapp.data.network.model.SuperherosDataResponseDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SuperheroService @Inject constructor(
    private val superheroApiClient: SuperheroApiClient
) {
    // private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getSuperheroes(superhero: String): SuperherosDataResponseDto {

        return withContext(Dispatchers.IO) {
            val response = superheroApiClient.getSuperheroes(superhero)
            response.body()!!
        }
    }

    suspend fun getSuperheroesDetail(superheroId: String): SuperheroDetailResponseDto {

        return withContext(Dispatchers.IO) {
            val response =
                superheroApiClient.getSuperheroDetail(superheroId)
            response.body()!!
        }
    }
}