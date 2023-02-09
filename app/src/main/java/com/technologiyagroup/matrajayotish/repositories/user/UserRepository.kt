package com.technologiyagroup.matrajayotish.repositories.user

import com.technologiyagroup.matrajayotish.model.user.NetworkResult
import com.technologiyagroup.matrajayotish.model.user.UserResponse
import com.technologiyagroup.matrajayotish.networkSetup.ApiService
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.catch

import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getUser(phone:String) = flow {
        emit(NetworkResult.Loading(true))
        val response = apiService.getUser(phone)
        emit(NetworkResult.Success(response))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }
}