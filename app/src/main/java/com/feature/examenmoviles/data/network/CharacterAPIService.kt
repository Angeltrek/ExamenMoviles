package com.feature.examenmoviles.data.network

import com.feature.examenmoviles.data.network.model.CharacterBase
import com.feature.examenmoviles.data.network.model.CharactersObject
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CharacterAPIService {
    @GET("characters")
    suspend fun getCharacters(
        @Query("page") page: Int? = null,  // Página que deseas solicitar
        @Query("limit") limit: Int? = 10    // Límite de personajes por página
    ): CharactersObject

    @GET("characters")
    suspend fun getCharacterByName(
        @Query("name") name: String
    ): List<CharacterBase>
}