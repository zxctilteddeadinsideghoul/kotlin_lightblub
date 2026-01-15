package com.example.blub.domain

import com.example.blub.data.model.BulbBrightnessLevel
import com.example.blub.data.repository.LightbulbRepository
import javax.inject.Inject

class GetBrightnessLevelUseCase @Inject constructor(
    private val repository: LightbulbRepository
) {
    suspend operator fun invoke(): BulbBrightnessLevel? {
        return repository.getBrightnessLevels()
    }
}

