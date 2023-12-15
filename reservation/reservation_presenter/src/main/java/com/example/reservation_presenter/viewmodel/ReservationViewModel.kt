package com.example.reservation_presenter.viewmodel


import com.example.navigation.base.BaseViewModel
import com.example.navigation.utils.UIState
import com.example.reservation_domain.model.Reservation
import com.example.reservation_domain.usecase.ReservationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor(
    private val reservationUseCase: ReservationUseCase): BaseViewModel() {

    private val _getReservationState = MutableStateFlow<UIState<Reservation>>(UIState.Empty())
    val getReservationState = _getReservationState.asStateFlow()

    fun getReservation(){
        reservationUseCase.getReservation().collectData(_getReservationState)
    }

}