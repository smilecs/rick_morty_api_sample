package com.smile.data.repository

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import com.past3.ketro.kcore.model.KResponse
import com.smile.domain.repository.CharacterLocalDataSource
import com.smile.domain.repository.CharacterRemoteDataSource
import com.smile.domain.repository.CharacterRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterRepositoryImplTest {

    private lateinit var characterRepositoryImpl: CharacterRepository

    @Mock
    private lateinit var remoteDataSource: CharacterRemoteDataSource

    @Mock
    private lateinit var localDataSource: CharacterLocalDataSource

    @Before
    fun setUp() {
        characterRepositoryImpl = CharacterRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `getCharacterList should return Wrapper List of Character`() {
        runBlocking {
            whenever(remoteDataSource.getCharacters()).thenReturn(getWrapperCharacterList())

            val data = characterRepositoryImpl.getCharacters()

            verify(localDataSource).save(getCharacterList())
            assertNotNull(data)
        }
    }

    @Test
    fun `getCharacterList should return Wrapper Error`() {
        runBlocking {
            whenever(remoteDataSource.getCharacters()).thenReturn(getWrapperErrorCharacterList())

            val data = characterRepositoryImpl.getCharacters()
            verifyZeroInteractions(localDataSource)
            assert(data is KResponse.Failure)
        }
    }

    @Test
    fun `saveImage should call localDataSource saveImage`() {
        runBlocking {
            characterRepositoryImpl.saveImage(DEFAULT_ID, EMPTY_STRING)
            verify(localDataSource).saveImage(DEFAULT_ID, EMPTY_STRING)
        }
    }

    @Test
    fun `loadCached should call localDataSource getCharacters`() {
        runBlocking {
            characterRepositoryImpl.loadCached()
            verify(localDataSource).getCharacters()
        }
    }

}