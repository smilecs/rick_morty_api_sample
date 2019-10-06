package com.smile.presentation.util

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Base64
import com.smile.presentation.R
import java.io.ByteArrayOutputStream


fun Drawable.drawableToBase64(): String {
    val stream = ByteArrayOutputStream()
    val bitmap = drawableToBitmap()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
    val imageBytes = stream.toByteArray()
    return Base64.encodeToString(imageBytes, Base64.DEFAULT)
}

fun Drawable.drawableToBitmap(): Bitmap {
    if (this is BitmapDrawable) {
        return this.bitmap
    }
    var width = intrinsicWidth
    width = if (width > 0) width else 1
    var height = intrinsicHeight
    height = if (height > 0) height else 1

    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    setBounds(0, 0, canvas.width, canvas.height)
    draw(canvas)
    return bitmap
}

fun String.toImage(resources: Resources): Drawable? {
    val decodedString = Base64.decode(this, Base64.DEFAULT)
    val decodedByte: Bitmap? = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    return decodedByte?.let {
        BitmapDrawable(resources, it)
    }
}

fun getErrorOrCacheDrawable(base64: String?, res: Resources): Drawable =
    base64?.toImage(res) ?: res.getDrawable(R.drawable.ic_directions_run_black_24dp)