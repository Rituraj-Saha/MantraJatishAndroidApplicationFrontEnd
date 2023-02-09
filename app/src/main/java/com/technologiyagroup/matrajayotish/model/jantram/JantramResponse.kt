package com.technologiyagroup.matrajayotish.model.jantram

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.technologiyagroup.matrajayotish.model.user.ResponseBody

data class JantramResponse(
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
