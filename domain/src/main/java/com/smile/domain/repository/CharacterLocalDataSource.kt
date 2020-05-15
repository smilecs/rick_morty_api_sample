package com.smile.domain.repository

import com.smile.domain.entities.Character
import kotlinx.coroutines.flow.Flow

interface CharacterLocalDataSource {

    fun getCharacters(): Flow<List<Character>>

    fun save(characters: List<Character>)

    fun saveImage(id: Int, image: String)

    fun delete()

}