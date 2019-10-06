package com.smile.domain

import com.past3.ketro.kcore.model.Wrapper

interface BaseApiWrapperUseCase<T, in Params> {

    suspend operator fun invoke(params: Params): Wrapper<T>

}
