package com.technologiyagroup.matrajayotish.viewModel.jantram

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technologiyagroup.matrajayotish.model.jantram.JantramResponse
import com.technologiyagroup.matrajayotish.model.user.NetworkResult
import com.technologiyagroup.matrajayotish.repositories.jantram.JantramRepository



import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JantramViewModel @Inject constructor(
    private val jantramRepostory: JantramRepository,
) : ViewModel() {
    private var _jantramResponse = MutableLiveData<NetworkResult<JantramResponse>>()
    val jantramResponse: LiveData<NetworkResult<JantramResponse>> = _jantramResponse
    suspend fun getJantram(star_id:String) {
        viewModelScope.launch {
            jantramRepostory.getJantram(star_id).collect {
                _jantramResponse.postValue(it)
            }
        }
    }
}