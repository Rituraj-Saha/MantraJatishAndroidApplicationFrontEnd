package com.technologiyagroup.matrajayotish.model.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    @Expose
    val id:Int,
    @SerializedName("name")
    @Expose
    val name:String,
    @SerializedName("phone")
    @Expose
    val phone:String,
    @SerializedName("date_of_birth")
    @Expose
    val dateOfBirth:String,
    @SerializedName("time_of_birth")
    @Expose
    val timeOfBirth:String,
    @SerializedName("place_of_birth")
    @Expose
    val placeOfBirth:String,
    @SerializedName("registration_date")
    @Expose
    val  registrationDate:String,
    @SerializedName("payment_status")
    @Expose
    val paymentStatus:String,
    @SerializedName("payment_amt")
    @Expose
    val paymentAmt:String,
    @SerializedName("payment_date")
    @Expose
    val paymentDate:String,
    @SerializedName("star_id")
    @Expose
    val starId:String,
    @SerializedName("session_status")
    @Expose
    val sessionStatus:String
)
