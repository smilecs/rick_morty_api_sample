package com.smile.data.mappers

import android.util.Log
import com.past3.ketro.kcore.model.KMapper
import com.smile.data.api.CharactersResultResponse
import com.smile.domain.entities.Character

class CharacterResultResponseMapper : KMapper<CharactersResultResponse, List<Character>>() {

    override fun mapFrom(from: CharactersResultResponse): List<Character> {
        return from.results.flatMap {
            listOf(
                Character(
                    id = it.id,
                    name = it.name,
                    status = it.status,
                    type = it.type,
                    species = it.species,
                    gender = it.gender,
                    origin = it.origin,
                    image = it.image
                )
            )
        }
    }
}