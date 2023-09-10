package com.vomisareg.hotel.retrofit

import com.vomisareg.hotel.model.BookingRoomModel
import com.vomisareg.hotel.model.HotelModel
import com.vomisareg.hotel.model.RoomListModel
import com.vomisareg.hotel.model.RoomModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface HotelServerApi {
   @GET("35e0d18e-2521-4f1b-a575-f0fe366f66e3")
   suspend fun getHotel():HotelModel

   @GET("f9a38183-6f95-43aa-853a-9c83cbb05ecd")
   suspend fun getRooms(): RoomListModel

   @GET("e8868481-743f-4eb2-a0d7-2bc4012275c8")
   suspend fun getRoomBooking():BookingRoomModel
}