package com.example.blub.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blub.data.model.BulbBrightnessLevel
import com.example.blub.data.model.BulbColorDto
import com.example.blub.domain.GetBrightnessLevelUseCase
import com.example.blub.domain.GetColorsUseCase
import com.example.blub.domain.SetBrightnessUseCase
import com.example.blub.domain.SetColorUseCase
import com.example.blub.domain.TurnOffUseCase
import com.example.blub.domain.TurnOnUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val turnOnUseCase: TurnOnUseCase,
    private val turnOffUseCase: TurnOffUseCase,
    private val setBrightnessUseCase: SetBrightnessUseCase,
    private val getBrightnessLevelUseCase: GetBrightnessLevelUseCase,
    private val setColorUseCase: SetColorUseCase,
    private val getColorsUseCase: GetColorsUseCase
) : ViewModel() {

    private val _brightnessLevelLiveData = MutableLiveData<BulbBrightnessLevel>()
    val brightnessLevelLiveData: LiveData<BulbBrightnessLevel> = _brightnessLevelLiveData

    private val _colorsLiveData = MutableLiveData<List<BulbColorDto>>()
    val colorsLiveData: LiveData<List<BulbColorDto>> = _colorsLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    fun loadInitialData() {
        viewModelScope.launch {
            try {
                val brightnessLevel = getBrightnessLevelUseCase()
                brightnessLevel?.let { _brightnessLevelLiveData.value = it }// FIXME

                val colors = getColorsUseCase()
                _colorsLiveData.value = colors
            } catch (e: Exception) {
                _errorLiveData.value = "Failed to load initial data: ${e.message}"
            }
        }
    }

    fun turnOn() {
        viewModelScope.launch {
            try {
                val result = turnOnUseCase()
                if (!result) {
                    _errorLiveData.value = "Failed to turn on the lightbulb"
                }
            } catch (e: Exception) {
                _errorLiveData.value = "Error: ${e.message}"
            }
        }
    }

    fun turnOff() {
        viewModelScope.launch {
            try {
                val result = turnOffUseCase()
                if (!result) {
                    _errorLiveData.value = "Failed to turn off the lightbulb"
                }
            } catch (e: Exception) {
                _errorLiveData.value = "Error: ${e.message}"
            }
        }
    }

    fun setBrightness(level: Int) {
        viewModelScope.launch {
            try {
                val result = setBrightnessUseCase(level)
                if (!result) {
                    _errorLiveData.value = "Failed to set brightness"
                }
            } catch (e: Exception) {
                _errorLiveData.value = "Error: ${e.message}"
            }
        }
    }

    fun setColor(colorName: String) {
        viewModelScope.launch {
            try {
                val result = setColorUseCase(colorName)
                if (!result) {
                    _errorLiveData.value = "Failed to set color"
                }
            } catch (e: Exception) {
                _errorLiveData.value = "Error: ${e.message}"
            }
        }
    }
}

