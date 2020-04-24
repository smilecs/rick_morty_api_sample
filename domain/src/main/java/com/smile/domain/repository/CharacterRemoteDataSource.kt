package com.smile.domain.repository

import com.past3.ketro.kcore.model.KResponse
import com.past3.ketro.kcore.model.Wrapper
import com.smile.domain.entities.Character

interface CharacterRemoteDataSource {

    suspend fun getCharacters(): KResponse<List<Character>>

}