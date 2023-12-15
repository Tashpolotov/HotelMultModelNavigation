package com.example.hotel_presenter.viewmodel


import com.example.hotel_domain.usecase.HotelUseCase
import com.example.hotels_domain.model.Hotels
import com.example.navigation.base.BaseViewModel
import com.example.navigation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HotelsViewModel @Inject constructor(private val getHotelsUseCase: HotelUseCase,):
    BaseViewModel(){

    private val _getHotelState = MutableStateFlow<UIState<Hotels>>(UIState.Empty())
    val getHotelStar = _getHotelState.asStateFlow()

    fun getHotels(){
        getHotelsUseCase.getHotels().collectData(_getHotelState)
    }


}