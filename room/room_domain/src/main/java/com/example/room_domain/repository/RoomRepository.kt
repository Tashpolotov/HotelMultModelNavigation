package com.example.room_domain.repository

import com.example.navigation.Resource
import com.example.room_domain.model.Rooms
import kotlinx.coroutines.flow.Flow

interface RoomRepository {
    fun getRooms(): Flow<Resource<Rooms>>
}