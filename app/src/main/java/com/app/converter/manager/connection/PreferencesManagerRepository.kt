package com.app.converter.manager.connection

import android.content.Context
import com.google.gson.Gson


class PreferencesManagerRepository(private val context: Context, private val gson: Gson) :
    PreferencesManager {

    companion object {
        const val COUNTRY = "country"
        const val USER = "user"
        const val DEVICE_TOKEN = "device_token"
        const val DEVICE_ID = "device_id"
        const val LANGUAGE = "language"
    }
}
