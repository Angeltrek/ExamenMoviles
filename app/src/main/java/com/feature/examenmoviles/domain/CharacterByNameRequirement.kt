package com.feature.examenmoviles.domain

import com.feature.examenmoviles.data.CharacterRepository
import com.feature.examenmoviles.data.network.model.CharacterBase

class CharacterByNameRequirement {
    private val repository = CharacterRepository()

    suspend operator fun invoke(name: String): List<CharacterBase> { // Asegúrate de que devuelva List<CharacterBase>
        return repository.getCharacterByName(name)
    }
}