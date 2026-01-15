package com.example.blub.data.repository

import com.example.blub.data.model.BulbBrightnessLevel
import com.example.blub.data.model.BulbColorDto

interface LightbulbRepository {
    suspend fun turnOn(): Boolean
    suspend fun turnOff(): Boolean
    suspend fun setBrightness(level: Int): Boolean
    suspend fun getBrightnessLevels(): BulbBrightnessLevel?
    suspend fun setColor(color: String): Boolean
    suspend fun getColors(): List<BulbColorDto>
}
