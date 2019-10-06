package com.smile.data.api

import com.smile.domain.entities.Origin

data class CharactersResponse(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val image: String
)