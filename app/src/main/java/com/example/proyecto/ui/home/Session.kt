package com.example.proyecto.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class Session {

    private var prefs: SharedPreferences = TODO()

    public fun Session(cntx: Context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx)
    }

    public fun setEmail(email: String) {
        prefs.edit().putString("email", email).commit()
    }

    public fun getEmail(): String? {
        val email: String? = prefs.getString("email", "")

        return email
    }
}