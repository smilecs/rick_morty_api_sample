package com.smile.domain.usecase

import com.smile.domain.BaseUseCase
import com.smile.domain.entities.Character
import com.smile.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersCacheUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) : BaseUseCase<List<Character>, Unit> {

    override suspend fun invoke(params: Unit): List<Character> =
        characterRepository.loadCached()

}