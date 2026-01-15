package com.example.blub.di

import com.example.blub.presenter.ui.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        AppModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    fun inject(fragment: MainFragment)
}

