package com.smile.data.repository

import android.util.Log
import com.past3.ketro.kcore.model.KResponse
import com.smile.domain.entities.Character
import com.smile.domain.repository.CharacterLocalDataSource
import com.smile.domain.repository.CharacterRemoteDataSource
import com.smile.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val localDataSource: CharacterLocalDataSource
) : CharacterRepository {

    override suspend fun getCharacters(): KResponse<List<Character>> {
        val resp = remoteDataSource.getCharacters()
        Log.i("chars22", resp.statusCode.toString())
        (resp as? KResponse.Success)?.data?.let {
            localDataSource.save(it)
        }
        return resp
    }

    override suspend fun saveImage(id: Int, image: String) {
        localDataSource.saveImage(id, image)
    }

    override suspend fun loadCached(): Flow<List<Character>> =
        localDataSource.getCharacters()

}
