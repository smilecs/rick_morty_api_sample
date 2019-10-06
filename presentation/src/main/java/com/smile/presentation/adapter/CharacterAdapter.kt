package com.smile.presentation.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smile.presentation.R
import com.smile.presentation.uimodel.CharacterUI

class CharacterAdapter(
    private val items: List<CharacterUI>,
    private val imageCache: (Int, Drawable) -> Unit,
    private val clickAction: (CharacterUI) -> Unit
) : RecyclerView.Adapter<CharacterAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return CharacterAdapterViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharacterAdapterViewHolder, position: Int) {
        holder.bind(items[position], imageCache, clickAction)
    }

}