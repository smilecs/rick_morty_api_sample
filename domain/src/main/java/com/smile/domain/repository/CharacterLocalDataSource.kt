package com.smile.domain.repository

import com.smile.domain.entities.Character

interface CharacterLocalDataSource {

    fun getCharacters(): List<Character>

    fun save(characters: List<Character>)

    fun saveImage(id: Int, image: String)

    fun delete()

}