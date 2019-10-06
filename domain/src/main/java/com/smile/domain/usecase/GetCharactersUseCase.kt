package com.smile.domain.usecase

import com.past3.ketro.kcore.model.Wrapper
import com.smile.domain.BaseApiWrapperUseCase
import com.smile.domain.entities.Character
import com.smile.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) : BaseApiWrapperUseCase<List<Character>, Unit> {

    override suspend fun invoke(params: Unit): Wrapper<List<Character>> =
        characterRepository.getCharacterList()

}