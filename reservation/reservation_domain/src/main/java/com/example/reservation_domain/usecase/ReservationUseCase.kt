package com.example.reservation_domain.usecase

import com.example.reservation_domain.repository.GetReservationRepository
import javax.inject.Inject

class ReservationUseCase @Inject constructor(
    private val getReservationRepository: GetReservationRepository
) {
    fun getReservation() = getReservationRepository.getReservation()
}