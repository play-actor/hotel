package com.vomisareg.hotel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.vomisareg.hotel.databinding.ActivityMainBinding
import com.vomisareg.hotel.di.ComponentManager.Companion.instance
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

   @Inject
   lateinit var navigatorHolder: NavigatorHolder

   private val navigator: Navigator = object : AppNavigator(this, R.id.root) {}
   private lateinit var viewModel: MainActivityViewModel
   private lateinit var binding: ActivityMainBinding

   override fun onCreate(savedInstanceState: Bundle?) {
      instance.appComponent.inject(this)
      super.onCreate(savedInstanceState)
      binding = ActivityMainBinding.inflate(layoutInflater)
      val view = binding.root
      setContentView(view)
      this.viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
      this.viewModel.initMainScrean(supportFragmentManager)
   }

   override fun onResume() {
      navigatorHolder.setNavigator(navigator)
      super.onResume()
   }

   override fun onPause() {
      navigatorHolder.removeNavigator()
      super.onPause()
   }
}