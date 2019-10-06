package com.smile.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "origin")
data class OriginEntity(
    @ColumnInfo(name = "origin_name")
    val name: String,
    val url: String
)