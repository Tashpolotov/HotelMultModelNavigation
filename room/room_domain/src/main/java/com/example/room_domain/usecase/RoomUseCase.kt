package com.example.room_domain.usecase

import com.example.room_domain.repository.RoomRepository
import javax.inject.Inject

class RoomUseCase @Inject constructor(private val repository: RoomRepository) {

    fun getRoom() = repository.getRooms()
}