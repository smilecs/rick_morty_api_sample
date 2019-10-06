package com.smile.presentation

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.past3.ketro.kcore.model.Wrapper
import com.past3.ketro.kcore.model.mapObject
import com.smile.domain.usecase.GetCharactersCacheUseCase
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
    private val loadCharactersCacheUseCase: GetCharactersCacheUseCase,
    private val imageUseCase: UpdateLocalImageUseCase
) : BaseViewModel() {

    private val _characterLiveData = MutableLiveData<Wrapper<List<CharacterUI>>>()
    val characterLiveData: LiveData<Wrapper<List<CharacterUI>>> = _characterLiveData

    init {
        getCharacters()
    }

    private fun getCharacters() {
        scope.launch(handler(::loadCachedCharacters)) {
            val resp = getCharactersUseCase(Unit).run {
                CharacterToUIMapper().mapObject(this)
            }
            uiScope.launch { _characterLiveData.value = resp }
        }
    }

    private fun loadCachedCharacters() {
        scope.launch(handler()) {
            val cachedData = loadCharactersCacheUseCase(Unit)
            CharacterToUIMapper().mapObject(Wrapper(data = cachedData))
                .run { _characterLiveData.postValue(this) }
        }
    }

    fun updateLocalImage(id: Int, image: Drawable) {
        scope.launch(handler()) {
            val params = UpdateImageParams(id, image.drawableToBase64())
            imageUseCase(params)
        }
    }

}