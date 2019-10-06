package com.smile.domain.usecase

import com.smile.domain.BaseUseCase
import com.smile.domain.repository.CharacterRepository
import javax.inject.Inject

class UpdateLocalImageUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) : BaseUseCase<Unit, UpdateImageParams> {

    override suspend fun invoke(params: UpdateImageParams) {
        characterRepository.saveImage(params.id, params.base64)
    }

}

class UpdateImageParams(val id: Int, val base64: String)