package bncc.net.ayosparring.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPref(val context: Context) {
    private val KEY_IS_LOGGED_IN = "isLoggedIn"
    private val PREF_NAME = "ayosparring"

    private val sharedPreferences = context.getSharedPreferences(PREF_NAME, 0)

    fun isLoggedIn(): Boolean = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)

    fun saveSession() {
        val editor = sharedPreferences.edit()
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.apply()
    }

    fun clearSession() {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.remove(KEY_IS_LOGGED_IN)
        editor.apply()
    }
}