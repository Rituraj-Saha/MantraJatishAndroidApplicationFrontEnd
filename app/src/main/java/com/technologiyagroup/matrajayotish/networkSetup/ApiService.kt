package com.technologiyagroup.matrajayotish.networkSetup

import com.technologiyagroup.matrajayotish.model.jantram.JantramResponse
import com.technologiyagroup.matrajayotish.model.mantra.MantramResponse
import com.technologiyagroup.matrajayotish.model.puja.PujaResponse
import com.technologiyagroup.matrajayotish.model.tips.TipsResponse
import com.technologiyagroup.matrajayotish.model.user.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/user/get-user")
    suspend fun getUser(@Query("phone") phone:String) : UserResponse

    @GET("/api/user/star/get-jantram")
    suspend fun getJantram(@Query("starid") star_id:String) : JantramResponse
    @GET("/api/user/star/get-jantram-info")
    suspend fun getJantramInfo(@Query("starid") star_id:String,@Query("lang") lang:String) : JantramResponse

    @GET("/api/user/star/get-mantram")
    suspend fun getMantra(@Query("starid") star_id:String) : MantramResponse

    @GET("/api/user/star/get-mantram-info")
    suspend fun getMantraInfo(@Query("starid") star_id:String,@Query("lang") lang:String) : MantramResponse

    @GET("/api/user/star/get-puja")
    suspend fun getPuja(@Query("starid") star_id:String,@Query("lang") lang:String) : PujaResponse

    @GET("/api/user/star/get-tips")
    suspend fun getTips(@Query("starid") star_id:String,@Query("lang") lang:String) : TipsResponse

    @GET("/api/user/star/get-gen-info")
    suspend fun getGenInfo(@Query("starid") star_id:String,@Query("lang") lang:String) : PujaResponse
}