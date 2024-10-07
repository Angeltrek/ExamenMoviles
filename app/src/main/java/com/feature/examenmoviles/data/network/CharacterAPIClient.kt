package com.feature.examenmoviles.data.network

class CharacterAPIClient {
    private lateinit var api: CharacterAPIService

    suspend fun getSomething(parameter: String): String? {
        api = NetworkModuleDI()
        return try {
            api.getSomething(parameter)
        } catch (e: Exception) { // Catch other exceptions
            e.printStackTrace()
            null
        }
    }

}