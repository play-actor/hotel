package com.vomisareg.hotel.ui.booking

import com.github.terrakok.cicerone.Router
import com.vomisareg.hotel.model.BookingRoomModel
import com.vomisareg.hotel.model.HotelModel
import com.vomisareg.hotel.navigation.Screens
import com.vomisareg.hotel.repository.DataRepository
import javax.inject.Inject

class BookingUseCase @Inject constructor(
   private var dataRepository: DataRepository,
   private var router: Router,
) {
   suspend fun getRoomBooking(): BookingRoomModel {
      return dataRepository.getRoomBooking()
   }

   fun back() {
      router.exit()
   }
   fun openHotel() {
      router.navigateTo(Screens.hotelScreen())
   }
}