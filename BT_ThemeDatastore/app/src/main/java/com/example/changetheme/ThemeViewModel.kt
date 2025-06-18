package com.example.changetheme

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ThemeViewModel(private val manager: ThemePreferenceManager) : ViewModel() {

    private val _themeColor = MutableStateFlow(Color.White)
    val themeColor: StateFlow<Color> = _themeColor

    init {
        viewModelScope.launch {
            manager.selectedColor.collect { color ->
                _themeColor.value = color
            }
        }
    }

    fun saveColor(color: Color) {
        viewModelScope.launch {
            manager.saveColor(color)
        }
    }
}

