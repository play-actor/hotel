package com.vomisareg.hotel.ui.rooms

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vomisareg.hotel.di.ComponentManager
import com.vomisareg.hotel.model.RoomListModel
import com.vomisareg.hotel.model.RoomModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomsViewModel : ViewModel() {
   @Inject
   lateinit var roomsUseCase: RoomsUseCase
   var mutableLiveData = MutableLiveData<RoomListModel>()

   init {
      ComponentManager.instance.appComponent.inject(this)
      loadRooms()
   }

   private fun loadRooms() {
      viewModelScope.launch {
         withContext(Dispatchers.IO) {
            val listLiveData = roomsUseCase.getRoomsList()
            withContext(Dispatchers.Main) { mutableLiveData.value = listLiveData }
         }
      }
   }

   fun back() {
      roomsUseCase.back()
   }

}