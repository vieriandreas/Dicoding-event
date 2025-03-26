package com.example.dicodingevent

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

abstract class BaseActivity : AppCompatActivity() {
    companion object {
        private const val KEY_THEME = "Theme"
        const val LIGHT = "light"
        const val DARK = "dark"
    }

    private var currentTheme = LIGHT

    override fun onCreate(savedInstanceState: Bundle?) {
        currentTheme = PreferenceManager
            .getDefaultSharedPreferences(this)
            .getString(KEY_THEME, LIGHT) ?: LIGHT
        super.onCreate(savedInstanceState)
    }

    protected fun setTheme() {
        if (currentTheme == LIGHT) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    protected fun switchTheme() {
        currentTheme = when (currentTheme) {
            LIGHT -> DARK
            DARK -> LIGHT
            else -> LIGHT
        }

        PreferenceManager.getDefaultSharedPreferences(this).edit().putString(KEY_THEME, currentTheme)
            .apply()
        recreate()
    }
}