package com.example.blub.di

import com.example.blub.data.network.LightbulbApiService
import com.example.blub.data.repository.LightbulbRepository
import com.example.blub.data.repository.LightbulbRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideLightbulbRepository(apiService: LightbulbApiService): LightbulbRepository {
        return LightbulbRepositoryImpl(apiService)
    }
}

