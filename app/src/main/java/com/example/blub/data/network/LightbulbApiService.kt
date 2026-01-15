package com.example.blub.data.network

import com.example.blub.data.model.BulbBrightnessLevel
import com.example.blub.data.model.BulbColorDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LightbulbApiService {
    @POST("state/on")
    suspend fun turnOn(): Boolean

    @POST("state/off")
    suspend fun turnOff(): Boolean

    @POST("brightness/")
    suspend fun setBrightness(@Query("level") level: Int): Boolean

    @GET("brightness/")
    suspend fun getBrightnessLevels(): BulbBrightnessLevel

    @POST("color/")
    suspend fun setColor(@Query("color") color: String): Boolean

    @GET("color/")
    suspend fun getColors(): List<BulbColorDto>
}

