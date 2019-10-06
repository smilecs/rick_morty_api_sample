package com.smile.rick_morty.di.repo

import com.smile.data.api.RickMortyAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    fun rickyMapi(retrofit: Retrofit): RickMortyAPI =
        retrofit.create(RickMortyAPI::class.java)

}