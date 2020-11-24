package com.app.converter.manager.connection

import com.google.gson.annotations.SerializedName

class Resource<T>   {
    var resourceStatus: ResourceStatus? = null

    @SerializedName("success") var success = false
    @SerializedName("timestamp") var timestamp = 0.0
    @SerializedName("base") var baseRate = "EUR"
    @SerializedName("date") var date = ""
    @SerializedName("rates") var data: T? = null
    @SerializedName("query") var queryData: T? = null

    enum class ResourceStatus {
        SUCCESS, FAILED, NO_CONNECTION, LOADING, LOGOUT, AUTHENTICATED, HIDE_LOADING
    }


}