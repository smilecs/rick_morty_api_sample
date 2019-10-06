package com.smile.data.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val base64: String?,
    val type: String,
    val gender: String,
    @Embedded
    val origin: OriginEntity,
    val image: String
)