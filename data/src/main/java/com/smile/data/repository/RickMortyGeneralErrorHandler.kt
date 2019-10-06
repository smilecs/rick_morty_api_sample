package com.smile.data.repository

import com.past3.ketro.api.ApiErrorHandler
import com.smile.data.TOO_MANY_REQUESTS
import com.smile.domain.repository.ErrorClass
import retrofit2.Response

class RickMortyGeneralErrorHandler : ApiErrorHandler() {

    override fun getExceptionType(response: Response<*>): Exception {
        return when (response.code()) {
            TOO_MANY_REQUESTS -> ErrorClass.TooManyRequests
            else -> ErrorClass.GeneralError
        }
    }

}