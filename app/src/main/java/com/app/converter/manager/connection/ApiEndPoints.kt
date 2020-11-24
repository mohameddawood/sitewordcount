package com.app.converter.manager.connection

import com.app.converter.BuildConfig.A_BASE_L
import com.app.converter.BuildConfig.A_KEY_I


object ApiEndPoints {
    const val BASE_URL = "${A_BASE_L}latest?access_key=${A_KEY_I}"
    const val RATES = "${BASE_URL}&format=1"
    const val CONVERT = "${A_BASE_L}convert?access_key=${A_KEY_I}"
}