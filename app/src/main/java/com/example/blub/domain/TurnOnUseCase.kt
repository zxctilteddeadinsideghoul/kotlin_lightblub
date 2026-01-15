package com.example.blub.domain

import com.example.blub.data.repository.LightbulbRepository
import javax.inject.Inject

class TurnOnUseCase @Inject constructor(
    private val repository: LightbulbRepository
) {
    suspend operator fun invoke(): Boolean {
        return repository.turnOn()
    }
}

