package com.smile.data.repository

import com.past3.ketro.kcore.model.KResponse
import com.smile.data.api.CharactersResponse
import com.smile.data.api.CharactersResultResponse
import com.smile.data.db.CharacterEntity
import com.smile.data.mappers.CharacterEntityMapper
import com.smile.domain.entities.Character
import okhttp3.ResponseBody
import retrofit2.Response

const val DEFAULT_ID = 1
const val EMPTY_STRING = ""

fun getWrapperCharacterList() =
    KResponse.Success(
        data = getCharacterList()
    )

fun getWrapperErrorCharacterList() =
    KResponse.Failure(exception = Exception())

fun getCharacterList() =
    listOf<Character>()

fun getApiCharacterResponse(): Response<CharactersResultResponse> =
    Response.success(200, provideResponseObject())

fun provideApiFailCharacterResponse(): Response<CharactersResultResponse> =
    Response.error(400, ResponseBody.create(null, byteArrayOf()))

fun provideResponseObject(): CharactersResultResponse =
    CharactersResultResponse(
        getCharacterResponse()
    )

fun getCharacterResponse(): List<CharactersResponse> =
    listOf<CharactersResponse>()

fun getCharacterEntityMapped(): List<Character> =
    CharacterEntityMapper()
        .mapFrom(getCharacterEntity())

fun getCharacterEntity(): List<CharacterEntity> =
    CharacterEntityMapper().mapTo(getCharacterList())