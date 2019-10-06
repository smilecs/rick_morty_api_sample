package com.smile.domain.usecase

import com.nhaarman.mockitokotlin2.whenever
import com.smile.domain.getWrapperCharacterList
import com.smile.domain.repository.CharacterRepository
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
            whenever(characterRepository.getCharacterList()).thenReturn(getWrapperCharacterList())
            val resp = getCharactersUseCase.invoke(Unit)
            assertNotNull(resp)
        }
    }

}