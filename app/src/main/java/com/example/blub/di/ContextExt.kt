package com.example.blub.di

import android.content.Context
import com.example.blub.BlubApplication

val Context.appComponent: AppComponent
    get() = (applicationContext as BlubApplication).appComponent

