package com.example.blub.domain

import com.example.blub.data.model.BulbColorDto
import com.example.blub.data.repository.LightbulbRepository
import javax.inject.Inject

class GetColorsUseCase @Inject constructor(
    private val repository: LightbulbRepository
) {
    suspend operator fun invoke(): List<BulbColorDto> {
        return repository.getColors()
    }
}

