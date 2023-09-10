package com.vomisareg.hotel

import android.app.Application
import com.vomisareg.hotel.di.ComponentManager.Companion.instance

class AppApplication : Application() {

   override fun onCreate() {
      instance.initAppComponent(applicationContext)
      instance.appComponent.inject(this)
      super.onCreate()
   }
}