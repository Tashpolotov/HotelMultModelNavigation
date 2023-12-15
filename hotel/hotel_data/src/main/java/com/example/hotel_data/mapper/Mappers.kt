package com.example.hotel_data.mapper

import com.example.hotel_data.model.HotelsModel
import com.example.hotels_domain.model.Hotels

fun HotelsModel.toHotels() = Hotels(
    about_the_hotel,
    adress,
    id,
    image_urls,
    minimal_price,
    name,
    price_for_it,
    rating,
    rating_name

)