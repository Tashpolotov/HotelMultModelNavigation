package com.example.hotelmultmodelnavigation


import com.example.hotel_data.repository.HotelsRepositoryImpl
import com.example.hotel_domain.repository.HotelRepository
import com.example.hotel_data.remote.service.HotelApiService
import com.example.reservation_data.remote.service.ReservationApiService
import com.example.reservation_data.repository.GetReservationRepositoryImpl
import com.example.reservation_domain.repository.GetReservationRepository
import com.example.room_data.remote.service.RoomApiService
import com.example.room_data.repository.RoomRepositoryImpl
import com.example.room_domain.repository.RoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): HotelApiService {
        return retrofit.create(HotelApiService::class.java)
    }

    @Provides
    fun provideRoomApiService(retrofit: Retrofit): RoomApiService {
        return retrofit.create(RoomApiService::class.java)
    }


    @Provides
    fun provideGetHotelsRepository(hotelApiService: HotelApiService): HotelRepository {
        return HotelsRepositoryImpl(hotelApiService)
    }

    @Provides
    fun provideRoomRepository(roomApiService: RoomApiService):RoomRepository{
        return RoomRepositoryImpl(roomApiService)
    }


    @Provides
    fun provideReservationRetrofit(retrofit: Retrofit): ReservationApiService {
        return retrofit.create(ReservationApiService::class.java)
    }


    @Provides
    fun provideReservationRepository(reservationApiService: ReservationApiService): GetReservationRepository {
        return GetReservationRepositoryImpl(reservationApiService)
    }
}