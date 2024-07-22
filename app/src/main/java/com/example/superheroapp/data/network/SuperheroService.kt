package com.example.superheroapp.data.network

import com.example.superheroapp.core.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SuperheroService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getSuperheroes(superhero: String): SuperheroDataResponse {

        return withContext(Dispatchers.IO) {
            val response = retrofit.create(SuperheroApiClient::class.java).getSuperheroes(superhero)

            response.body() ?: SuperheroDataResponse("no superhero", emptyList())
        }
    }
}