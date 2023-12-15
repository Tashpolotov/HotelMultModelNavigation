package com.example.room_data.mapper

import com.example.room_data.model.RoomsModel
import com.example.room_domain.model.Rooms


fun RoomsModel.toRooms() = Rooms(
    rooms
)