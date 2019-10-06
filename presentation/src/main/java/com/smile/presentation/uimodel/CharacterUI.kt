package com.smile.presentation.uimodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CharacterUI(
    val id: Int,
    val name: String,
    val status: String,
    val type: String,
    val gender: String,
    val image: String,
    val base64: String?
) : Parcelable