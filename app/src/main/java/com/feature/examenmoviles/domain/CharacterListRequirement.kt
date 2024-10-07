package com.feature.examenmoviles.domain

import com.feature.examenmoviles.data.CharacterRepository

class CharacterListRequirement {
    private val repository = CharacterRepository()

    suspend operator fun invoke(page: Int? = null, limit: Int = 10) = repository.getCharacters(page, limit)
}