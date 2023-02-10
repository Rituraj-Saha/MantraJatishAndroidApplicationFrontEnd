package com.technologiyagroup.matrajayotish.repositories.tips

import com.technologiyagroup.matrajayotish.model.user.NetworkResult
import com.technologiyagroup.matrajayotish.networkSetup.ApiService
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TipsRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getTips(star_id:String,lang:String) = flow {
        emit(NetworkResult.Loading(true))
        val response = apiService.getTips(star_id,lang)
        emit(NetworkResult.Success(response))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }
}