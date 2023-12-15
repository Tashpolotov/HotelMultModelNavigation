package com.example.hotel_data.remote.service

import com.example.hotel_data.model.HotelsModel
import retrofit2.http.GET

interface HotelApiService {


    @GET("d144777c-a67f-4e35-867a-cacc3b827473")
    suspend fun getHotels(): HotelsModel


}