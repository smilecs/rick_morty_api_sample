package com.smile.domain.repository

import com.past3.ketro.kcore.model.Wrapper
import com.smile.domain.entities.Character

interface CharacterRepository {

    suspend fun getCharacterList(): Wrapper<List<Character>>

    suspend fun loadCached(): List<Character>

    suspend fun saveImage(id: Int, image: String)

}