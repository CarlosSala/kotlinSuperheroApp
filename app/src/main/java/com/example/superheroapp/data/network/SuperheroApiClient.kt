package com.example.superheroapp.data.network

import com.example.superheroapp.data.network.model.SuperherosDataResponseDto
import com.example.superheroapp.data.network.model.SuperheroDetailResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperheroApiClient {

    @GET("api/10229233666327556/search/{name}")
    suspend fun getSuperheroes(@Path("name") superheroName: String): Response<SuperherosDataResponseDto>

    @GET("api/10229233666327556/{id}")
    suspend fun getSuperheroDetail(@Path("id") superheroId: String): Response<SuperheroDetailResponseDto>
}