package com.codecamp.newsx.prefs

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class ScrollStatePrefs @Inject constructor(context: Context) {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    }

    private val INDEX_KEY = "scroll_key"

    // Sample function to get a value from SharedPreferences
    fun getIndexValueFromPrefs(): Int {
        return sharedPreferences.getInt(INDEX_KEY, 0)
    }

    // Sample function to save a value to SharedPreferences
    fun saveIndexValueToPrefs(index: Int) {
        sharedPreferences.edit().putInt(INDEX_KEY, index).apply()
    }
}