package com.vomisareg.hotel.ui.hotel

import com.vomisareg.hotel.model.HotelModel
import com.vomisareg.hotel.repository.DataRepository
import javax.inject.Inject

class HotelUseCase @Inject constructor(
   private var dataRepository: DataRepository,
) {
   suspend fun getHotelInfo(): HotelModel {
      return dataRepository.getHotelInfo()
   }
}