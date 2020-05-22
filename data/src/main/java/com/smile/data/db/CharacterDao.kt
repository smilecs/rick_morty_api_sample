package com.smile.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character")
    fun getAll(): Flow<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(characterEntity: List<CharacterEntity>)

    @Query("UPDATE character SET base64 = :image WHERE id = :id")
    fun updateImage(id: Int, image: String)

    @Query("DELETE FROM character")
    fun nuke()

}