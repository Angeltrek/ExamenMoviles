package com.feature.examenmoviles.data.network

import com.feature.examenmoviles.data.network.model.CharacterBase
import com.feature.examenmoviles.data.network.model.CharactersObject

class CharacterAPIClient {
    private lateinit var api: CharacterAPIService

    suspend fun getCharacters(page: Int? = null, limit: Int = 10): CharactersObject? {
        api = NetworkModuleDI()
        return try {
            api.getCharacters(
                page = page,
                limit = limit
            )
        } catch (e: Exception) { // Catch other exceptions
            e.printStackTrace()
            null
        }
    }

    suspend fun getCharacterByName(name: String): List<CharacterBase> {
        api = NetworkModuleDI()
        return try {
            api.getCharacterByName(name) // Asegúrate de que este método retorne una lista
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList() // Retornar una lista vacía en caso de error
        }
    }

}