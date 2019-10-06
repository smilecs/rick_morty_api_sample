package com.smile.data.mappers

import com.past3.ketro.kcore.model.KMapper
import com.smile.data.db.CharacterEntity
import com.smile.domain.entities.Character

class CharacterEntityMapper : KMapper<List<CharacterEntity>, List<Character>>() {

    override fun mapFrom(from: List<CharacterEntity>): List<Character> =
        from.flatMap {
            listOf(
                Character(
                    id = it.id,
                    name = it.name,
                    status = it.status,
                    type = it.type,
                    species = it.species,
                    gender = it.gender,
                    origin = OriginEntityMapper().mapFrom(it.origin),
                    image = it.image,
                    base64 = it.base64
                )
            )

        }

    fun mapTo(to: List<Character>): List<CharacterEntity> =
        to.flatMap {
            listOf(
                CharacterEntity(
                    id = it.id,
                    name = it.name,
                    status = it.status,
                    type = it.type,
                    species = it.species,
                    gender = it.gender,
                    origin = OriginEntityMapper().mapTo(it.origin),
                    image = it.image,
                    base64 = it.base64
                )
            )

        }

}