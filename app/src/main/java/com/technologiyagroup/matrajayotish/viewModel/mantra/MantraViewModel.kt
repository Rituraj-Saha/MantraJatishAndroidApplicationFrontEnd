package com.technologiyagroup.matrajayotish.viewModel.mantra

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technologiyagroup.matrajayotish.model.jantram.JantramResponse
import com.technologiyagroup.matrajayotish.model.mantra.MantramResponse
import com.technologiyagroup.matrajayotish.model.user.NetworkResult
import com.technologiyagroup.matrajayotish.repositories.mantra.MantraRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MantraViewModel @Inject constructor(
    private val mantraRepository: MantraRepository,
) : ViewModel() {
    private var _mantraResponse = MutableLiveData<NetworkResult<MantramResponse>>()
    val mantraResponse: LiveData<NetworkResult<MantramResponse>> = _mantraResponse
    suspend fun getMantra(star_id:String) {
        viewModelScope.launch {
            mantraRepository.getMantra(star_id).collect {
                _mantraResponse.postValue(it)
            }
        }
    }
}