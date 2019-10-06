package com.smile.rick_morty.di.view

import com.smile.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @PerView
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

}