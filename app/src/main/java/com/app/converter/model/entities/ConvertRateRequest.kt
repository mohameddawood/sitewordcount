package com.app.converter.model.entities

import com.google.gson.annotations.SerializedName

class ConvertRateRequest {
    @SerializedName("from")
    var from: String = ""
    @SerializedName("to")
    var to: String = ""
    @SerializedName("amount")
    var amount: String = ""
}