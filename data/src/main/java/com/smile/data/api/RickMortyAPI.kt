package com.smile.data.api

import retrofit2.Response
import retrofit2.http.GET

interface RickMortyAPI {

    @GET("/api/character/")
    suspend fun getCharacters(): Response<CharactersResultResponse>

}