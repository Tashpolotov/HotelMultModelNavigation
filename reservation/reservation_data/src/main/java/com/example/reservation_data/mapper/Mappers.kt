package com.example.reservation_data.mapper

import com.example.reservation_data.model.ReservationModel
import com.example.reservation_domain.model.Reservation


fun ReservationModel.toReservation() = Reservation(
    arrival_country,
    departure,
    fuel_charge,
    horating,
    hotel_adress,
    hotel_name,
    id,
    number_of_nights,
    nutrition,
    rating_name,
    room,
    service_charge,
    tour_date_start,
    tour_date_stop,
    tour_price
)