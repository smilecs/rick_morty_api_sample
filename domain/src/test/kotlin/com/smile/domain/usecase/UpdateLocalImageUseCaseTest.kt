package com.smile.domain.usecase

import com.nhaarman.mockitokotlin2.verify
import com.smile.domain.DEFAULT_ID
import com.smile.domain.EMPTY_STRING
import com.smile.domain.repository.CharacterRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UpdateLocalImageUseCaseTest {

    private lateinit var updateLocalImageUseCase: UpdateLocalImageUseCase
    @Mock
    private lateinit var characterRepository: CharacterRepository

    @Before
    fun setUp() {
        updateLocalImageUseCase = UpdateLocalImageUseCase(characterRepository)
    }

    @Test
    fun `invoke should call saveImage`() {
        runBlocking {
            updateLocalImageUseCase.invoke(UpdateImageParams(DEFAULT_ID, EMPTY_STRING))
            verify(characterRepository).saveImage(DEFAULT_ID, EMPTY_STRING)
        }
    }

}