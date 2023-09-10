package com.vomisareg.hotel.repository

import com.vomisareg.hotel.model.BookingRoomModel
import com.vomisareg.hotel.model.HotelModel
import com.vomisareg.hotel.model.RoomListModel
import com.vomisareg.hotel.retrofit.HotelServerApi
import javax.inject.Inject

class DataRepository @Inject constructor() {


   @Inject
   lateinit var api: HotelServerApi

   suspend fun getHotelInfo(): HotelModel {
      return api.getHotel()
   }

   suspend fun getRoomsList(): RoomListModel {
      return api.getRooms()
   }

   suspend fun getRoomBooking(): BookingRoomModel {
      return api.getRoomBooking()
   }


}