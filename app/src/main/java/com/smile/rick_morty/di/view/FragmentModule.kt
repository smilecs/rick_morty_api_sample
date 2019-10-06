package com.smile.rick_morty.di.view

import com.smile.presentation.DetailCharacterFragment
import com.smile.presentation.MainActivityFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @PerView
    @ContributesAndroidInjector
    abstract fun mainFragment(): MainActivityFragment

    @PerView
    @ContributesAndroidInjector
    abstract fun detailFragment(): DetailCharacterFragment

}