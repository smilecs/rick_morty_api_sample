package com.smile.data.repository.datasource

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.smile.data.db.CharacterDao
import com.smile.data.db.CharacterEntity
import com.smile.data.repository.DEFAULT_ID
import com.smile.data.repository.EMPTY_STRING
import com.smile.data.repository.getCharacterEntity
import com.smile.data.repository.getCharacterList
import com.smile.domain.repository.CharacterLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterLocalDataSourceImplTest {

    private lateinit var characterLocalDataSource: CharacterLocalDataSource

    @Mock
    private lateinit var dao: CharacterDao


    @Before
    fun setUp() {
        characterLocalDataSource = CharacterLocalDataSourceImpl(dao)
    }

    @Test
    fun `getCharacters should return Character List`() {
        runBlocking {
            whenever(dao.getAll()).thenReturn(flowOf(getCharacterEntity()))
            val data = characterLocalDataSource.getCharacters()
            verify(dao).getAll()
            data.collect {
                assertNotNull(it)
            }
        }
    }

    @Test
    fun `save should call nuke and save`() {
        characterLocalDataSource.save(getCharacterList())
        verify(dao).nuke()
        verify(dao).save(getCharacterEntity())
    }

    @Test
    fun `saveImage should call updateImage`() {
        characterLocalDataSource.saveImage(DEFAULT_ID, EMPTY_STRING)
        verify(dao).updateImage(DEFAULT_ID, EMPTY_STRING)
    }

    @Test
    fun `delete should call nuke`() {
        characterLocalDataSource.delete()
        verify(dao).nuke()
    }

}