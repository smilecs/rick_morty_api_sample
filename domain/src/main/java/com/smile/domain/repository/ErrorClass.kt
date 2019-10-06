package com.smile.domain.repository

sealed class ErrorClass(msg: String = "") : Exception(msg) {
    object TooManyRequests : ErrorClass("Exceeded usage allowance")
    object GeneralError : ErrorClass()
    object NoInternet : ErrorClass()
}