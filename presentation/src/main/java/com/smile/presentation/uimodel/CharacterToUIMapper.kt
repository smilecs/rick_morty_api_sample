package com.smile.presentation.uimodel

import com.past3.ketro.kcore.model.KMapper
import com.smile.domain.entities.Character

class CharacterToUIMapper : KMapper<List<Character>, List<CharacterUI>>() {

    override fun mapFrom(from: List<Character>): List<CharacterUI> {
        return from.map {
            CharacterUI(
                id = it.id,
                name = it.name,
                status = it.status,
                type = it.type,
                gender = it.gender,
                image = it.image,
                base64 = it.base64
            )
        }
    }

}