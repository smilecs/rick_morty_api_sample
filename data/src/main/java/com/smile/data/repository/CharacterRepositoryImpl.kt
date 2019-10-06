package com.smile.data.repository

import com.past3.ketro.kcore.model.Wrapper
import com.smile.domain.repository.CharacterLocalDataSource
import com.smile.domain.repository.CharacterRemoteDataSource
import com.smile.domain.entities.Character
import com.smile.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val localDataSource: CharacterLocalDataSource
) : CharacterRepository {

    override suspend fun getCharacterList(): Wrapper<List<Character>> {
        val resp = remoteDataSource.getCharacters()
        resp.data?.let {
            localDataSource.save(it)
        }
        return resp
    }

    override suspend fun saveImage(id: Int, image: String) {
        localDataSource.saveImage(id, image)
    }

    override suspend fun loadCached(): List<Character> =
        localDataSource.getCharacters()

}
