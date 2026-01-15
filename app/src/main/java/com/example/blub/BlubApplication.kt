package com.example.blub

import android.app.Application
import com.example.blub.di.AppComponent
import com.example.blub.di.DaggerAppComponent

class BlubApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}

