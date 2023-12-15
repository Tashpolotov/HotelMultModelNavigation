package com.example.hotel_domain.repository

import com.example.hotels_domain.model.Hotels
import com.example.navigation.Resource
import kotlinx.coroutines.flow.Flow

interface HotelRepository {

    fun getHotels(): Flow<Resource<Hotels>>
}