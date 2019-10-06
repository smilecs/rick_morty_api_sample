package com.smile.rick_morty.di


import android.app.Application
import android.content.Context
import androidx.room.Room
import com.smile.data.db.CharacterDao
import com.smile.rick_morty.RickMortyApplication
import com.smile.data.db.CharacterDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideRoomDataBase(application: RickMortyApplication): CharacterDatabase =
        Room.databaseBuilder(
            application,
            CharacterDatabase::class.java, "database-name"
        ).build()

    @Provides
    fun providesCharacterDao(characterDatabase: CharacterDatabase): CharacterDao =
        characterDatabase.characterDao()

}