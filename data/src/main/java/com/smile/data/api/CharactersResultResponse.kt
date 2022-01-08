package com.smile.data.api

data class CharactersResultResponse(
    val info: Info,
    val results: List<CharactersResponse>
)

data class Info(val count: Int, val pages: Int, val next: String, val prev: Int?)