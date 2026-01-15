package com.example.blub.domain

import com.example.blub.data.repository.LightbulbRepository
import javax.inject.Inject

class SetColorUseCase @Inject constructor(
    private val repository: LightbulbRepository
) {
    suspend operator fun invoke(color: String): Boolean {
        return repository.setColor(color)
    }
}

