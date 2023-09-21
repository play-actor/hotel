package com.vomisareg.hotel.ui.booking

import com.vomisareg.hotel.model.BookingRoomModel
import com.vomisareg.hotel.repository.DataRepository
import javax.inject.Inject

class BookingUseCase @Inject constructor(
   private var dataRepository: DataRepository,
) {
   suspend fun getRoomBooking(): BookingRoomModel {
      return dataRepository.getRoomBooking()
   }

}