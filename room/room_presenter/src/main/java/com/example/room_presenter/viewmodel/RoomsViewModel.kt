package com.example.room_presenter.viewmodel


import com.example.room_domain.model.Rooms
import com.example.room_domain.usecase.RoomUseCase
import com.example.navigation.base.BaseViewModel
import com.example.navigation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(private val getRoomsUseCase: RoomUseCase): BaseViewModel() {

    private val _getRoomsState = MutableStateFlow<UIState<Rooms>>(UIState.Empty())
    val getRoomsState = _getRoomsState.asStateFlow()

    fun getRooms(){
        getRoomsUseCase.getRoom().collectData(_getRoomsState)
    }
}