package com.smile.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.smile.data.db.CharacterDao
import com.smile.data.db.CharacterEntity


@Database(entities = [CharacterEntity::class], version = 1)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

}
