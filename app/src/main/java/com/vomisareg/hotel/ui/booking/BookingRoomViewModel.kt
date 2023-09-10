package com.vomisareg.hotel.ui.booking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vomisareg.hotel.di.ComponentManager
import com.vomisareg.hotel.model.BookingRoomModel
import com.vomisareg.hotel.navigation.Screens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookingRoomViewModel : ViewModel() {
   @Inject
   lateinit var bookingUseCase: BookingUseCase

   val modelMutableLiveData = MutableLiveData<BookingRoomModel>()

   init {
      ComponentManager.instance.appComponent.inject(this)
      loadBookingRoom()
   }

   private fun loadBookingRoom() {
      viewModelScope.launch {
         withContext(Dispatchers.IO) {
            val bookingRoomModel = bookingUseCase.getRoomBooking()
            withContext(Dispatchers.Main) { modelMutableLiveData.value = bookingRoomModel }
         }
      }
   }

   fun back() {
      bookingUseCase.back()
   }
   fun openHotel() {
      bookingUseCase.openHotel()
   }

}