package com.technologiyagroup.matrajayotish.repositories.mantra

import com.technologiyagroup.matrajayotish.model.user.NetworkResult
import com.technologiyagroup.matrajayotish.networkSetup.ApiService
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MantraRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getMantra(star_id:String) = flow {
        emit(NetworkResult.Loading(true))
        val response = apiService.getMantra(star_id)
        emit(NetworkResult.Success(response))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

    suspend fun getMantraInfo(star_id:String,lang:String) = flow {
        emit(NetworkResult.Loading(true))
        val response = apiService.getMantraInfo(star_id,lang)
        emit(NetworkResult.Success(response))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }
}