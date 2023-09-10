package com.vomisareg.hotel.navigation

import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.vomisareg.hotel.ui.booking.BookingRoomFragment
import com.vomisareg.hotel.ui.hotel.HotelFragment
import com.vomisareg.hotel.ui.rooms.RoomsFragment

object Screens {

   @JvmStatic
   fun hotelScreen() = object : FragmentScreen {
      override fun createFragment(factory: FragmentFactory) =
         HotelFragment()
   }

   @JvmStatic
   fun roomScreen(hotelName:String) = object : FragmentScreen {
      override fun createFragment(factory: FragmentFactory) =
         RoomsFragment().apply {
            val bundle = Bundle()
            bundle.putString("hotel_name", hotelName)
            arguments = bundle
         }
   }

   @JvmStatic
   fun bookingScreen() = object : FragmentScreen {
      override fun createFragment(factory: FragmentFactory) =
         BookingRoomFragment()
   }
}