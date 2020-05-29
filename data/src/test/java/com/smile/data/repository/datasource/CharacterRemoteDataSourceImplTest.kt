package com.smile.data.repository.datasource

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.past3.ketro.kcore.model.KResponse
import com.smile.data.api.RickMortyAPI
import com.smile.data.repository.getApiCharacterResponse
import com.smile.data.repository.provideApiFailCharacterResponse
import com.smile.domain.repository.CharacterRemoteDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterRemoteDataSourceImplTest {

    private lateinit var characterRemoteDataSourceImpl: CharacterRemoteDataSource

    @Mock
    private lateinit var rickMortyAPI: RickMortyAPI

    @Before
    fun setUp() {
        characterRemoteDataSourceImpl = CharacterRemoteDataSourceImpl(rickMortyAPI)
    }

    @Test
    fun `getCharacters should return Wrapper List of Character`() {
        runBlocking {
            whenever(rickMortyAPI.getCharacters()).thenReturn(getApiCharacterResponse())
            val data = characterRemoteDataSourceImpl.getCharacters()
            verify(rickMortyAPI).getCharacters()
            Assert.assertNotNull(data is KResponse.Success)
        }
    }

    @Test
    fun `getCharacters should return Wrapper Error`() {
        runBlocking {
            whenever(rickMortyAPI.getCharacters()).thenReturn(provideApiFailCharacterResponse())
            val data = characterRemoteDataSourceImpl.getCharacters()
            verify(rickMortyAPI).getCharacters()
            assert(data is KResponse.Failure)
        }
    }

}