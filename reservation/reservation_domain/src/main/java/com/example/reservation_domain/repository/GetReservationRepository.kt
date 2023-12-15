package com.example.reservation_domain.repository


import com.example.navigation.Resource
import com.example.reservation_domain.model.Reservation
import kotlinx.coroutines.flow.Flow

interface GetReservationRepository {
    fun getReservation(): Flow<Resource<Reservation>>
}