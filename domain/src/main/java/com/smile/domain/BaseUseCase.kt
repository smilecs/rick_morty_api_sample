package com.smile.domain

interface BaseUseCase<T, in Params> {

    suspend operator fun invoke(params: Params): T

}