package com.vomisareg.hotel.di

import android.content.Context

class ComponentManager private constructor() {

   companion object {
      val instance by lazy { ComponentManager() }
   }

   lateinit var appComponent: AppComponent

   fun initAppComponent(context: Context) {
      this.appComponent = DaggerAppComponent.factory().create(context)
   }

}