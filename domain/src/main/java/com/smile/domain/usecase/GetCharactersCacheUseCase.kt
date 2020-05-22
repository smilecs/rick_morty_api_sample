package com.smile.domain.usecase

import com.smile.domain.entities.Character
import com.smile.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersCacheUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {

    suspend operator fun invoke(): Flow<List<Character>> =
        characterRepository.loadCached()

}