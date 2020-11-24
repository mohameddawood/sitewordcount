package com.app.converter.manager.utilities

import android.text.TextUtils
import android.util.Patterns

object Validation {
    fun isEmail(str: CharSequence?): Boolean {
        return str != null && Patterns.EMAIL_ADDRESS.matcher(str).matches()
    }

    fun isPhone(str: CharSequence?): Boolean {
        return str != null && Patterns.PHONE.matcher(str).matches()
    }

    fun isPassword(password: String): Boolean {
        return password.length >= Constants.PASSWORD_LENGTH
    }

    fun isPasswordMatch(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

    fun isNullOrEmpty(string: String?): Boolean {
        return TextUtils.isEmpty(string)
    }
}