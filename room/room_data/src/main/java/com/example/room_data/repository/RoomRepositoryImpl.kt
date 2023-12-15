package com.example.room_data.repository

import com.example.navigation.base.BaseRepository
import com.example.room_data.mapper.toRooms
import com.example.room_data.remote.service.RoomApiService
import com.example.room_domain.repository.RoomRepository
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val apiService: RoomApiService): BaseRepository(), RoomRepository {
    override fun getRooms() = doRequest {
        apiService.getRooms().toRooms()
    }
}