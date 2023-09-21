package com.vomisareg.hotel.ui.rooms

import com.vomisareg.hotel.model.RoomListModel
import com.vomisareg.hotel.repository.DataRepository
import javax.inject.Inject

class RoomsUseCase @Inject constructor(
   private var dataRepository: DataRepository
) {
   suspend fun getRoomsList(): RoomListModel {
      return dataRepository.getRoomsList()
   }
}