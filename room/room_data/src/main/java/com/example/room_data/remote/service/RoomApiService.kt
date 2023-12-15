package com.example.room_data.remote.service

import com.example.room_data.model.RoomsModel
import retrofit2.http.GET

interface RoomApiService {

    @GET("8b532701-709e-4194-a41c-1a903af00195")
    suspend fun getRooms(): RoomsModel

}