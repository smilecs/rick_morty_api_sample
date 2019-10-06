package com.smile.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smile.domain.repository.ErrorClass
import kotlinx.coroutines.*
import java.io.IOException

abstract class BaseViewModel : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val uiJob = SupervisorJob()

    protected val scope = CoroutineScope(
        Dispatchers.Default
                + viewModelJob
    )

    protected val uiScope = CoroutineScope(
        Dispatchers.Main
                + uiJob
    )

    private val _failure: MutableLiveData<ErrorClass> = MutableLiveData()
    val failure: LiveData<ErrorClass> = _failure


    fun handler(action: (() -> Any)? = null): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, throwable ->
            when (throwable) {
                is IOException -> {
                    uiScope.launch {
                        action?.invoke()
                        onInternetError()
                    }
                }
                else -> {
                    throwable.printStackTrace()
                }
            }
        }
    }

    open fun onInternetError() {
        _failure.value = ErrorClass.NoInternet
    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }


}