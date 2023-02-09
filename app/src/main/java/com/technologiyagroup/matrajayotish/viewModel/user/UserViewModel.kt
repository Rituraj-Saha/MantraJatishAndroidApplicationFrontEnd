package com.technologiyagroup.matrajayotish.viewModel.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technologiyagroup.matrajayotish.model.user.NetworkResult
import com.technologiyagroup.matrajayotish.model.user.ResponseBody
import com.technologiyagroup.matrajayotish.model.user.User
import com.technologiyagroup.matrajayotish.model.user.UserResponse
import com.technologiyagroup.matrajayotish.repositories.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel  @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel(){

//    private var _movieResponse = MutableLiveData<NetworkResult<List<User>>>()
    private var _userResponse = MutableLiveData<NetworkResult<UserResponse>>()
    val userResponse: LiveData<NetworkResult<UserResponse>> = _userResponse
//    init {
//        u_phone = phone
//        getUser(u_phone)
//    }
    suspend fun getUser(phone:String) {
        viewModelScope.launch {
            userRepository.getUser(phone).collect {
                _userResponse.postValue(it)
            }
        }
    }
}