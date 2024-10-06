package com.feature.examenmoviles.data

import com.feature.examenmoviles.data.network.APIClient

class Repository {
    private val api = APIClient()

    suspend fun getSomething(parameter: String) = api.getSomething(parameter)
}