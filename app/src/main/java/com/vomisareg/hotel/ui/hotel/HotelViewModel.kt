package com.vomisareg.hotel.ui.hotel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import com.vomisareg.hotel.di.ComponentManager
import com.vomisareg.hotel.model.HotelModel
import com.vomisareg.hotel.navigation.Screens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HotelViewModel : ViewModel() {
   @Inject
   lateinit var hotelUseCase: HotelUseCase

   @Inject
   lateinit var router: Router

   val hotelModelMutableLiveData = MutableLiveData<HotelModel>()

   init {
      ComponentManager.instance.appComponent.inject(this)
      loadHotel()
   }

   private fun loadHotel() {
      viewModelScope.launch {
         withContext(Dispatchers.IO) {
            val hotelModel = hotelUseCase.getHotelInfo()
            withContext(Dispatchers.Main) { hotelModelMutableLiveData.value = hotelModel }
         }
      }
   }

   fun openRoom(hotelName: String) {
      router.navigateTo(Screens.roomScreen(hotelName))
   }

}