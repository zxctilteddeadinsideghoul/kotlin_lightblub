package com.example.blub.domain

import com.example.blub.data.repository.LightbulbRepository
import javax.inject.Inject

class SetBrightnessUseCase @Inject constructor(
    private val repository: LightbulbRepository
) {
    suspend operator fun invoke(level: Int): Boolean {
        return repository.setBrightness(level)
    }
}

