package com.technologiyagroup.matrajayotish.networkSetup

import com.technologiyagroup.matrajayotish.model.jantram.JantramResponse
import com.technologiyagroup.matrajayotish.model.mantra.MantramResponse
import com.technologiyagroup.matrajayotish.model.user.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/user/get-user")
    suspend fun getUser(@Query("phone") phone:String) : UserResponse

    @GET("/api/user/star/get-jantram")
    suspend fun getJantram(@Query("starid") star_id:String) : JantramResponse

    @GET("/api/user/star/get-mantram")
    suspend fun getMantra(@Query("starid") star_id:String) : MantramResponse
}