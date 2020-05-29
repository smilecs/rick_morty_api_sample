package com.smile.domain.usecase

import com.nhaarman.mockitokotlin2.whenever
import com.smile.domain.getCharacterList
import com.smile.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCharactersCacheUseCaseTest {

    private lateinit var getCharactersCacheUseCase: GetCharactersCacheUseCase

    @Mock
    private lateinit var characterRepository: CharacterRepository

    @Before
    fun setUp() {
        getCharactersCacheUseCase = GetCharactersCacheUseCase(characterRepository)
    }

    @Test
    fun `invoke should return Character List`() {
        runBlocking {
            whenever(characterRepository.loadCached()).thenReturn(flowOf(getCharacterList()))
            val resp = getCharactersCacheUseCase.invoke()
            resp.collect {
                Assert.assertNotNull(it)
            }
        }
    }

}