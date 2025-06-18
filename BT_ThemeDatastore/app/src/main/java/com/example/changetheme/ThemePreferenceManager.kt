package com.example.changetheme

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.toColorInt
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "theme_prefs")

object ThemeKeys {
    val COLOR = stringPreferencesKey("theme_color")
}

class ThemePreferenceManager(private val context: Context) {

    val selectedColor: Flow<Color> = context.dataStore.data.map { preferences ->
        val colorHex = preferences[ThemeKeys.COLOR]
        try {
            if (colorHex != null) {
                Color(colorHex.toColorInt())
            } else {
                Color.White
            }
        } catch (e: IllegalArgumentException) {
            Color.White
        }
    }

    suspend fun saveColor(color: Color) {
        val rgb = String.format("#%06X", 0xFFFFFF and color.toArgb())
        context.dataStore.edit { preferences ->
            preferences[ThemeKeys.COLOR] = rgb
        }
    }
}

