package com.feature.examenmoviles.data

import com.feature.examenmoviles.data.network.CharacterAPIClient

class CharacterRepository {
    private val api = CharacterAPIClient()

    suspend fun getSomething(parameter: String) = api.getSomething(parameter)
}