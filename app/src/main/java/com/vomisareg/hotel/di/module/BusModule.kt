package com.vomisareg.hotel.di.module

import com.vomisareg.hotel.bus.EventHandler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BusModule {

   @Singleton
   @Provides
   fun provideEventHandler(): EventHandler {
      return EventHandler()
   }
}