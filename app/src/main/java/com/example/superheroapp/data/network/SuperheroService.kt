package com.example.superheroapp.data.network

import com.example.superheroapp.core.RetrofitHelper
import com.example.superheroapp.data.network.model.SuperheroDetailResponseDto
import com.example.superheroapp.data.network.model.SuperherosDataResponseDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SuperheroService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getSuperheroes(superhero: String): SuperherosDataResponseDto {

        return withContext(Dispatchers.IO) {
            val response = retrofit.create(SuperheroApiClient::class.java).getSuperheroes(superhero)

            response.body() ?: SuperherosDataResponseDto("no superhero", emptyList())
        }
    }

    suspend fun getSuperheroesDetail(superheroId: String): SuperheroDetailResponseDto {

        return withContext(Dispatchers.IO) {
            val response =
                retrofit.create(SuperheroApiClient::class.java).getSuperheroDetail(superheroId)
            response.body()!!
        }
    }
}