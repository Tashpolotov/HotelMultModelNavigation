package com.example.reservation_data.repository


import com.example.navigation.base.BaseRepository
import com.example.reservation_data.mapper.toReservation
import com.example.reservation_data.remote.service.ReservationApiService
import com.example.reservation_domain.repository.GetReservationRepository
import javax.inject.Inject

class GetReservationRepositoryImpl @Inject constructor(
    private val apiService: ReservationApiService
) : BaseRepository(), GetReservationRepository {

    override fun getReservation() = doRequest {
        apiService.getReservation().toReservation()
    }
}