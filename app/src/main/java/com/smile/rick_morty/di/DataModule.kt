package com.smile.rick_morty.di

import com.smile.rick_morty.di.repo.ApiModule
import com.smile.rick_morty.di.repo.NetModule
import dagger.Module

@Module(
    includes = [
        NetModule::class,
        ApiModule::class,
        RepoModule::class
    ]
)
class DataModule