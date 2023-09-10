package com.vomisareg.hotel.ui.hotel

import com.github.terrakok.cicerone.Router
import com.vomisareg.hotel.model.HotelModel
import com.vomisareg.hotel.navigation.Screens
import com.vomisareg.hotel.repository.DataRepository
import javax.inject.Inject

class HotelUseCase @Inject constructor(
   private var dataRepository: DataRepository,
   private var router: Router,
) {
   suspend fun getHotelInfo(): HotelModel {
      return dataRepository.getHotelInfo()
   }

   fun openRoom(hotelName:String) {
      router.navigateTo(Screens.roomScreen(hotelName))
   }

}