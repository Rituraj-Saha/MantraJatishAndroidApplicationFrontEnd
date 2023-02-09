package com.technologiyagroup.matrajayotish.model.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseBody(
    @SerializedName("user")
    @Expose
    val user:User

)