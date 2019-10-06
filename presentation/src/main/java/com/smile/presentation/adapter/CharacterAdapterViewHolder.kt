package com.smile.presentation.adapter

import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import coil.target.Target
import com.smile.presentation.R
import com.smile.presentation.uimodel.CharacterUI
import com.smile.presentation.util.getErrorOrCacheDrawable
import kotlinx.android.synthetic.main.list_item.view.*

class CharacterAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(
        character: CharacterUI,
        imageCache: (Int, Drawable) -> Unit,
        clickAction: (CharacterUI) -> Unit
    ) {
        itemView.previewBanner.load(character.image) {
            crossfade(true)
            placeholder(R.drawable.ic_directions_run_black_24dp)
            scale(Scale.FILL)
            target(object : Target {
                override fun onError(error: Drawable?) {
                    super.onError(error)
                    itemView.previewBanner.setImageDrawable(
                        getErrorOrCacheDrawable(character.base64, itemView.resources)
                    )
                }

                override fun onSuccess(result: Drawable) {
                    super.onSuccess(result)
                    itemView.previewBanner.setImageDrawable(result)
                    imageCache(character.id, result)
                }
            })
        }
        itemView.characterName.text = character.name
        itemView.setOnClickListener {
            clickAction(character)
        }
    }

}