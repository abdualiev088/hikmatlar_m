package com.example.hikmatlar.custom

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

    fun isFirstLaunch(): Boolean {
        return preferences.getBoolean("is_IntroA_first_launch", true)
    }

    fun markFirstLaunch() {
        val editor = preferences.edit()
        editor.putBoolean("is_IntroA_first_launch", false)
        editor.apply()
    }
}