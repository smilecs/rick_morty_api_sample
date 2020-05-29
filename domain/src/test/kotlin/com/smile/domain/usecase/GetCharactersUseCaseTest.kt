package com.smile.domain.usecase

import com.nhaarman.mockitokotlin2.whenever
import com.past3.ketro.kcore.model.KResponse
import com.smile.domain.getCharacterList
import com.smile.domain.getWrapperCharacterList
import com.smile.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCharactersUseCaseTest {

    private lateinit var getCharactersUseCase: GetCharactersUseCase

    @Mock
    private lateinit var characterRepository: CharacterRepository

    @Before
    fun setUp() {
        getCharactersUseCase = GetCharactersUseCase(characterRepository)
    }

    @Test
    fun `invoke should return Wrapper Character List`() {
        runBlocking {
            whenever(characterRepository.getCharacters()).thenReturn(
                KResponse.Success(
                    getCharacterList()
                )
            )
            val resp = getCharactersUseCase.invoke()

            assertNotNull(resp)
        }
    }

}