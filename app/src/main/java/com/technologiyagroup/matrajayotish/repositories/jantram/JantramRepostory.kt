package com.technologiyagroup.matrajayotish.repositories.jantram

import com.technologiyagroup.matrajayotish.model.user.NetworkResult
import com.technologiyagroup.matrajayotish.networkSetup.ApiService
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class JantramRepostory  @Inject constructor(private val apiService: ApiService) {

    suspend fun getJantram(star_id:String) = flow {
        emit(NetworkResult.Loading(true))
        val response = apiService.getJantram(star_id)
        emit(NetworkResult.Success(response))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }
}