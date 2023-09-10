package com.vomisareg.hotel.ui.rooms

import com.github.terrakok.cicerone.Router
import com.vomisareg.hotel.model.RoomListModel
import com.vomisareg.hotel.navigation.Screens
import com.vomisareg.hotel.repository.DataRepository
import javax.inject.Inject

class RoomsUseCase @Inject constructor(
   private var dataRepository: DataRepository,
   private var router: Router,
) {
   suspend fun getRoomsList(): RoomListModel {
      return dataRepository.getRoomsList()
   }

   fun back() {
      router.exit()
   }
}