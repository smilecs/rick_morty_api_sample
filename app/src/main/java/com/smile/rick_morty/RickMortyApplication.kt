package com.smile.rick_morty


import coil.Coil
import coil.ImageLoader
import coil.util.CoilUtils
import com.smile.rick_morty.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import okhttp3.OkHttpClient
import javax.inject.Inject

class RickMortyApplication @Inject constructor() : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()
        initGlobalImageLoader()
    }

    private fun initGlobalImageLoader() {
        Coil.setDefaultImageLoader {
            ImageLoader(this) {
                crossfade(true)
                okHttpClient {
                    OkHttpClient.Builder()
                        .cache(CoilUtils.createDefaultCache(this@RickMortyApplication))
                        .build()
                }
            }
        }
    }

}