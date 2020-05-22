package com.smile.domain.usecase

import com.past3.ketro.kcore.model.KResponse
import com.smile.domain.entities.Character
import com.smile.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {

    suspend operator fun invoke(): KResponse<List<Character>> =
        characterRepository.getCharacters()

}