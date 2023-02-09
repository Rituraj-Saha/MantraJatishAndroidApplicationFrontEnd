package com.technologiyagroup.matrajayotish.model.puja

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PujaResponse(
    @SerializedName("response_code")
    @Expose
    val responseCode:String,
    @SerializedName("response_msg")
    @Expose
    val responseMsg:String,
    @SerializedName("response_body")
    @Expose
    val responseBody: String
)
