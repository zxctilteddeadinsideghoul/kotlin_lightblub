package com.example.blub.data.repository

import com.example.blub.data.model.BulbBrightnessLevel
import com.example.blub.data.model.BulbColorDto
import com.example.blub.data.network.LightbulbApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LightbulbRepositoryImpl @Inject constructor(
    private val apiService: LightbulbApiService
) : LightbulbRepository {
    override suspend fun turnOn(): Boolean = try {
        apiService.turnOn()
    } catch (e: Exception) {
        false
    }

    override suspend fun turnOff(): Boolean = try {
        apiService.turnOff()
    } catch (e: Exception) {
        false
    }

    override suspend fun setBrightness(level: Int): Boolean = try {
        apiService.setBrightness(level)
    } catch (e: Exception) {
        false
    }

    override suspend fun getBrightnessLevels(): BulbBrightnessLevel? = try {
        apiService.getBrightnessLevels()
    } catch (e: Exception) {
        null
    }

    override suspend fun setColor(color: String): Boolean = try {
        apiService.setColor(color)
    } catch (e: Exception) {
        false
    }

    override suspend fun getColors(): List<BulbColorDto> = try {
        apiService.getColors()
    } catch (e: Exception) {
        emptyList()
    }
}

