package com.example.hotel_domain.usecase

import com.example.hotel_domain.repository.HotelRepository
import javax.inject.Inject

class HotelUseCase @Inject constructor(private val repository: HotelRepository) {

        fun getHotels() = repository.getHotels()
}