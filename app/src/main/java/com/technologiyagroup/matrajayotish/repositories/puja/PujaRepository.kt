package com.technologiyagroup.matrajayotish.repositories.puja

import com.technologiyagroup.matrajayotish.model.user.NetworkResult
import com.technologiyagroup.matrajayotish.networkSetup.ApiService
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PujaRepository @Inject constructor(private val apiService: ApiService){
    suspend fun getPuja(star_id:String) = flow {
        emit(NetworkResult.Loading(true))
        val response = apiService.getPuja(star_id)
        emit(NetworkResult.Success(response))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }
}