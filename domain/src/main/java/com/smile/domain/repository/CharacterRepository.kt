package com.smile.domain.repository

import com.past3.ketro.kcore.model.KResponse
import com.past3.ketro.kcore.model.Wrapper
import com.smile.domain.entities.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    suspend fun getCharacters(): KResponse<List<Character>>

    suspend fun loadCached(): Flow<List<Character>>

    suspend fun saveImage(id: Int, image: String)

}