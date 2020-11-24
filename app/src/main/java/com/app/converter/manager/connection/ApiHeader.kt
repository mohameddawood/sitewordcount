package com.app.converter.manager.connection

import com.google.gson.annotations.SerializedName

class ApiHeader {
    @SerializedName("Authorization") var authorization: String
    @SerializedName("country_id") var countryId: Int? = 0
    @SerializedName("device-uuid") var deviceUUID: String = "android"
    @SerializedName("device-type") var deviceType: String = "android"
    @SerializedName("device-id") var deviceId: String
    @SerializedName("language-id") var languageId = "1"
    @SerializedName("language") var language = "en"
    @SerializedName(value = "device-token") var deviceToken: String
    @SerializedName(value = "x-api-key") var apiKey = "NWRjYWY1NTYzZjkwOGVlNzNjYjliZWJiMzA4NWFlZWNjODU5ZGNjMzQ4ZDBiOThmMTE0NjczMjBmMzI5MzQxZDNkZDdkNWE4ODI5YWI3YzRkNjJjYjQwODJkODA1YmI0ZGMxNWZhMmI3YmMwODk3OTFlY2VkODQwMjUwNGU2YWI="

    //add new comments
    constructor(authorization: String = "", countryId: Int = 0,
                deviceUUID: String = "", deviceId: String = "", deviceToken: String = "", languageId: String = "1",language: String) {
        this.authorization = String.format("Bearer %s", authorization)
        this.countryId = countryId
        this.deviceUUID = deviceUUID
        this.deviceId = deviceId
        this.deviceToken = deviceToken
        this.languageId = languageId
        this.language = language
    }
}