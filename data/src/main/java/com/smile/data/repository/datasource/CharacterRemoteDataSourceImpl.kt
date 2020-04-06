package com.smile.data.repository.datasource


import com.past3.ketro.api.Request
import com.past3.ketro.kcore.model.Wrapper
import com.past3.ketro.kcore.model.mapObject
import com.smile.data.api.CharactersResultResponse
import com.smile.data.api.RickMortyAPI
import com.smile.data.mappers.CharacterResultResponseMapper
import com.smile.data.repository.RickMortyGeneralErrorHandler
import com.smile.domain.entities.Character
import com.smile.domain.repository.CharacterRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class CharacterRemoteDataSourceImpl @Inject constructor(
    private val rickMortyAPI: RickMortyAPI
) : CharacterRemoteDataSource {

    override suspend fun getCharacters(): Wrapper<List<Character>> {
        val req = object : Request<CharactersResultResponse>(
            RickMortyGeneralErrorHandler()
        ) {
            override suspend fun apiRequest(): Response<CharactersResultResponse> =
                rickMortyAPI.getCharacters()
        }.doRequest()
        return CharacterResultResponseMapper()
            .mapObject(req)
    }

}