package com.vomisareg.hotel.di

import android.content.Context
import com.vomisareg.hotel.AppApplication
import com.vomisareg.hotel.MainActivity
import com.vomisareg.hotel.MainActivityViewModel
import com.vomisareg.hotel.adapter.AddTouristsDelegateAdapter
import com.vomisareg.hotel.adapter.HotelMainDelegateAdapter
import com.vomisareg.hotel.adapter.PriceDelegateAdapter
import com.vomisareg.hotel.adapter.RoomDelegateAdapter
import com.vomisareg.hotel.adapter.TouristsDelegateAdapter
import com.vomisareg.hotel.di.module.BusModule
import com.vomisareg.hotel.di.module.ImageModule
import com.vomisareg.hotel.di.module.NavigationModule
import com.vomisareg.hotel.di.module.NetworkModule
import com.vomisareg.hotel.ui.booking.BookingRoomFragment
import com.vomisareg.hotel.ui.booking.BookingRoomViewModel
import com.vomisareg.hotel.ui.hotel.HotelFragment
import com.vomisareg.hotel.ui.hotel.HotelViewModel
import com.vomisareg.hotel.ui.rooms.RoomsViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NavigationModule::class, BusModule::class, ImageModule::class, NetworkModule::class])
interface AppComponent {
   @Component.Factory
   interface Factory {
      fun create(@BindsInstance context: Context): AppComponent
   }

   fun inject(classes: AppApplication)
   fun inject(classes: MainActivity)
   fun inject(classes: MainActivityViewModel)
   fun inject(classes: HotelViewModel)
   fun inject(classes: RoomsViewModel)
   fun inject(classes: RoomDelegateAdapter)
   fun inject(classes: HotelMainDelegateAdapter)
   fun inject(classes: BookingRoomFragment)
   fun inject(classes: BookingRoomViewModel)
   fun inject(classes: TouristsDelegateAdapter)
   fun inject(classes: PriceDelegateAdapter)
   fun inject(classes: AddTouristsDelegateAdapter)
}