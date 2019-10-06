package com.smile.rick_morty.di


import com.smile.data.repository.CharacterRepositoryImpl
import com.smile.domain.repository.CharacterLocalDataSource
import com.smile.domain.repository.CharacterRemoteDataSource
import com.smile.data.repository.datasource.CharacterLocalDataSourceImpl
import com.smile.data.repository.datasource.CharacterRemoteDataSourceImpl
import com.smile.domain.repository.CharacterRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepoModule {

    @Binds
    abstract fun provideCharacterRepo(characterRepositoryImpl: CharacterRepositoryImpl): CharacterRepository

    @Binds
    abstract fun providelocalCharDataSource(localDataSourceImpl: CharacterLocalDataSourceImpl): CharacterLocalDataSource

    @Binds
    abstract fun provideRemoteDataSource(remoteDataSourceImpl: CharacterRemoteDataSourceImpl): CharacterRemoteDataSource

}