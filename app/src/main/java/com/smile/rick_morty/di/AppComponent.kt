package com.smile.rick_morty.di

import com.smile.rick_morty.RickMortyApplication
import com.smile.rick_morty.di.view.ActivityModule
import com.smile.rick_morty.di.view.FragmentModule
import com.smile.rick_morty.di.view.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        DataModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        FragmentModule::class
    ]
)
interface AppComponent : AndroidInjector<RickMortyApplication> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: RickMortyApplication): Builder

        fun build(): AppComponent
    }

}
