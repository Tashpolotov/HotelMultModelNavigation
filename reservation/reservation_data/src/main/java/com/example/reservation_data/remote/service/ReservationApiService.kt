package com.example.reservation_data.remote.service


import com.example.reservation_data.model.ReservationModel
import retrofit2.http.GET

    interface ReservationApiService {

        @GET("63866c74-d593-432c-af8e-f279d1a8d2ff")
        suspend fun getReservation(): ReservationModel
}