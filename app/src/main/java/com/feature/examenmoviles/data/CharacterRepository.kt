package com.feature.examenmoviles.data

import com.feature.examenmoviles.data.network.CharacterAPIClient
import com.feature.examenmoviles.data.network.model.CharacterBase

class CharacterRepository {
    private val api = CharacterAPIClient()

    suspend fun getCharacters(page: Int? = null, limit: Int = 10) = api.getCharacters(page, limit)
    // Asegúrate de que el tipo de retorno sea List<CharacterBase>
    suspend fun getCharacterByName(name: String): List<CharacterBase> {
        return api.getCharacterByName(name) // Asegúrate de que el cliente devuelva la lista correcta
    }
}