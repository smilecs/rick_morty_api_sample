package com.smile.presentation

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.past3.ketro.api.LiveDataHandler
import com.smile.domain.usecase.GetCharactersUseCase
import com.smile.domain.usecase.UpdateImageParams
import com.smile.domain.usecase.UpdateLocalImageUseCase
import com.smile.presentation.base.BaseViewModel
import com.smile.presentation.uimodel.CharacterToUIMapper
import com.smile.presentation.uimodel.CharacterUI
import com.smile.presentation.util.drawableToBase64
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val imageUseCase: UpdateLocalImageUseCase
) : BaseViewModel() {

    private val _characterLiveData = MutableLiveData<List<CharacterUI>>()
    val characterLiveData: LiveData<List<CharacterUI>> = _characterLiveData
    private val liveDataHandler = LiveDataHandler(failure)

    init {
        getCharacters()
    }

    private fun getCharacters() {
        scope.launch(handler()) {
            val resp = getCharactersUseCase()
            uiScope.launch {
                liveDataHandler.parse(resp) { data ->
                    data?.let {
                        _characterLiveData.value = CharacterToUIMapper().mapFrom(it)
                    }
                }
            }
        }
    }

    fun updateLocalImage(id: Int, image: Drawable) {
        scope.launch(handler()) {
            val params = UpdateImageParams(id, image.drawableToBase64())
            imageUseCase(params)
        }
    }

}