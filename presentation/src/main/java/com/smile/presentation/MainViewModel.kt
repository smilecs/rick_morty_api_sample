package com.smile.presentation

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.past3.ketro.api.LiveDataHandler
import com.past3.ketro.kcore.model.KResponse
import com.smile.domain.entities.Character
import com.smile.domain.usecase.GetCharactersUseCase
import com.smile.domain.usecase.UpdateImageParams
import com.smile.domain.usecase.UpdateLocalImageUseCase
import com.smile.presentation.base.BaseViewModel
import com.smile.presentation.uimodel.CharacterToUIMapper
import com.smile.presentation.uimodel.CharacterUI
import com.smile.presentation.util.drawableToBase64
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.IllegalStateException
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val imageUseCase: UpdateLocalImageUseCase
) : BaseViewModel() {

    private val _characterLiveData = MutableLiveData<List<CharacterUI>>()
    val characterLiveData: LiveData<List<CharacterUI>> = _characterLiveData
    private val liveDataHandler = LiveDataHandler(_failureLiveData)
    val characterItems = mutableListOf<CharacterUI>()

    init {
        getCharacters()
    }

    private fun getCharacters() {
        scope.launch(handler()) {
            val resp: KResponse<List<Character>> = getCharactersUseCase()
            withContext(Dispatchers.Main) {
                emitCharacterUIData(resp)
            }

        }
    }

    private fun emitCharacterUIData(kResponse: KResponse<List<Character>>) {
        liveDataHandler.parse(kResponse) { data ->
            data?.let {
                characterItems.addAll(CharacterToUIMapper().mapFrom(it))
                _characterLiveData.value = characterItems
            } ?: {
                _failureLiveData.value = IllegalStateException()
            }()
        }
    }

    fun updateLocalImage(id: Int, image: Drawable) {
        scope.launch(handler()) {
            val params = UpdateImageParams(id, image.drawableToBase64())
            imageUseCase(params)
        }
    }

}