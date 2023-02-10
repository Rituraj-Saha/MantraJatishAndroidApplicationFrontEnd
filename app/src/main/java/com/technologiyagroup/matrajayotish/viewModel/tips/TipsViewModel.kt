package com.technologiyagroup.matrajayotish.viewModel.tips

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technologiyagroup.matrajayotish.model.puja.PujaResponse
import com.technologiyagroup.matrajayotish.model.tips.TipsResponse
import com.technologiyagroup.matrajayotish.model.user.NetworkResult
import com.technologiyagroup.matrajayotish.repositories.puja.PujaRepository
import com.technologiyagroup.matrajayotish.repositories.tips.TipsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TipsViewModel @Inject constructor(
    private val tipsRepository: TipsRepository,
) : ViewModel(){
    private var _tipsResponse = MutableLiveData<NetworkResult<TipsResponse>>()
    val tipsResponse: LiveData<NetworkResult<TipsResponse>> = _tipsResponse
    suspend fun getTips(star_id:String,lang:String) {
        viewModelScope.launch {
            tipsRepository.getTips(star_id,lang).collect {
                _tipsResponse.postValue(it)
            }
        }
    }
}