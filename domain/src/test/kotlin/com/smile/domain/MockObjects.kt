package com.smile.domain

import com.past3.ketro.kcore.model.Wrapper
import com.smile.domain.entities.Character

const val DEFAULT_ID = 1
const val EMPTY_STRING = ""

fun getWrapperCharacterList() =
    Wrapper(
        data = getCharacterList()
    )

fun getCharacterList() =
    listOf<Character>()