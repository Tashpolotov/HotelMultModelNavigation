package com.example.hotel_data.repository

import com.example.navigation.base.BaseRepository
import com.example.hotel_data.mapper.toHotels
import com.example.hotel_data.remote.service.HotelApiService
import com.example.hotel_domain.repository.HotelRepository
import javax.inject.Inject

class HotelsRepositoryImpl @Inject constructor(
    private val apiService: HotelApiService
): BaseRepository(), HotelRepository {
    override fun getHotels() = doRequest {
        apiService.getHotels().toHotels()
    }
}