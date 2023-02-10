package com.technologiyagroup.matrajayotish.viewModel.puja

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technologiyagroup.matrajayotish.model.jantram.JantramResponse
import com.technologiyagroup.matrajayotish.model.puja.PujaResponse
import com.technologiyagroup.matrajayotish.model.user.NetworkResult

import com.technologiyagroup.matrajayotish.repositories.puja.PujaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PujaViewModel @Inject constructor(
    private val pujaRepostory: PujaRepository,
) : ViewModel()  {
    private var _pujaResponse = MutableLiveData<NetworkResult<PujaResponse>>()
    val pujaResponse: LiveData<NetworkResult<PujaResponse>> = _pujaResponse
    suspend fun getPuja(star_id:String,lang:String) {
        viewModelScope.launch {
            pujaRepostory.getPuja(star_id,lang).collect {
                _pujaResponse.postValue(it)
            }
        }
    }
}