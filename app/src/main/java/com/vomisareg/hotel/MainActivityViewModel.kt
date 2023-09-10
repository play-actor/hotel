package com.vomisareg.hotel

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.vomisareg.hotel.di.ComponentManager
import com.vomisareg.hotel.navigation.Screens
import com.vomisareg.hotel.repository.DataRepository
import javax.inject.Inject

class MainActivityViewModel : ViewModel() {
   @Inject
   lateinit var router: Router

   @Inject
   lateinit var dataRepository: DataRepository

   init {
      ComponentManager.instance.appComponent.inject(this)
   }

   fun initMainScrean(supportFragmentManager: FragmentManager) {
      if (supportFragmentManager.fragments.isEmpty()) router.navigateTo(Screens.hotelScreen())
   }
}