package com.vomisareg.hotel.ui.booking

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import com.vomisareg.hotel.R
import com.vomisareg.hotel.adapter.AddTouristsDelegateAdapter
import com.vomisareg.hotel.adapter.AddTouristsModelItem
import com.vomisareg.hotel.adapter.BookingDescriptionDelegateAdapter
import com.vomisareg.hotel.adapter.BookingDescriptionModelItem
import com.vomisareg.hotel.adapter.BookingMainDelegateAdapter
import com.vomisareg.hotel.adapter.BookingMainModelItem
import com.vomisareg.hotel.adapter.BuyerInfoDelegateAdapter
import com.vomisareg.hotel.adapter.BuyerInfoModelItem
import com.vomisareg.hotel.adapter.CompositeDelegateAdapter
import com.vomisareg.hotel.adapter.PriceDelegateAdapter
import com.vomisareg.hotel.adapter.PriceModelItem
import com.vomisareg.hotel.adapter.TouristsDelegateAdapter
import com.vomisareg.hotel.adapter.TouristsModelItem
import com.vomisareg.hotel.bus.BusEvent
import com.vomisareg.hotel.bus.EventHandler
import com.vomisareg.hotel.databinding.BuyerInfoBinding
import com.vomisareg.hotel.databinding.FragmentRoomBookingBinding
import com.vomisareg.hotel.databinding.TouristItemBinding
import com.vomisareg.hotel.di.ComponentManager
import com.vomisareg.hotel.repository.DataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookingRoomFragment : Fragment() {
   private var viewModel: BookingRoomViewModel? = null
   private var _binding: FragmentRoomBookingBinding? = null
   private val binding get() = _binding!!

   @Inject
   lateinit var dataRepository: DataRepository

   @Inject
   lateinit var eventHandler: EventHandler
   private val scope = CoroutineScope(Dispatchers.Main + Job())
   private lateinit var modelList: MutableList<Any>
   private val comAdapter = lazy {
      context?.let {
         CompositeDelegateAdapter(
            BookingMainDelegateAdapter(it),
            BookingDescriptionDelegateAdapter(it),
            BuyerInfoDelegateAdapter(it),
            TouristsDelegateAdapter(it),
            AddTouristsDelegateAdapter(it),
            PriceDelegateAdapter(it)
         )
      }
   }
   private var idTourist: Int = 0

   init {
      ComponentManager.instance.appComponent.inject(this)
   }

   private fun subscribeOnEventBus() {
      eventHandler.subscribeEvent { busEvent ->
         (busEvent as? BusEvent.Refresh)?.apply {
            refresh()
         }
         false
      }
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      this.viewModel = ViewModelProvider(this)[BookingRoomViewModel::class.java]
      subscribeOnEventBus()
   }


   @SuppressLint("NotifyDataSetChanged")
   private fun refresh() {
      modelList.add(modelList.lastIndex - 1, TouristsModelItem(idTourist++))
      this.binding.apply {
         comAdapter.value?.swapData(modelList)
         scope.launch {
            rv.adapter?.notifyDataSetChanged()
         }
      }
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?,
   ): View {
      _binding = FragmentRoomBookingBinding.inflate(inflater, container, false)
      val view = binding.root
      binding.apply {
         motionLayout.transitionToStart()
         toolbarMain.setNavigationOnClickListener {
            viewModel?.back()
         }
         toolbarMainEnd.setNavigationOnClickListener {
            this.motionLayout.transitionToStart()
         }
         btnFinish.setOnClickListener {
            viewModel?.openHotel()
         }
         viewModel?.modelMutableLiveData?.observe(viewLifecycleOwner) { vm ->
            modelList = mutableListOf(
               BookingMainModelItem(
                  vm.id,
                  vm.hotel_name,
                  vm.hotel_adress,
                  vm.horating,
                  vm.rating_name,
               ),
               BookingDescriptionModelItem(
                  vm.hotel_name,
                  vm.departure,
                  vm.arrival_country,
                  vm.tour_date_start,
                  vm.tour_date_stop,
                  vm.number_of_nights,
                  vm.room,
                  vm.nutrition,
               ),
               BuyerInfoModelItem(vm.id),
               TouristsModelItem(idTourist++),
               AddTouristsModelItem(vm.id),
               PriceModelItem(
                  vm.id,
                  vm.tour_price,
                  vm.fuel_charge,
                  vm.service_charge
               ) { paymentVerification() }
            )
            this.rv.apply {
               layoutManager = LinearLayoutManager(context)
               adapter = comAdapter.value
               comAdapter.value?.swapData(modelList)
            }
         }
      }
      return view
   }

   private fun paymentVerification() {
      if (validate()) {
         this.binding.motionLayout.transitionToEnd()
      }
   }

   private fun validateText(vararg fields: TextInputEditText): Boolean {
      var isCorrect = true
      fields.forEach { field ->
         if (field.text.isNullOrEmpty()) {
            field.setBackgroundColor(resources.getColor(R.color.error))
            isCorrect = false
         }
      }
      return isCorrect
   }

   private fun validate(): Boolean {
      var isCorrect = true
      binding.rv.children.forEach { item ->
         try {
            val tourist = TouristItemBinding.bind(item)
            if (!validateText(
                  tourist.firstNameText,
                  tourist.lastNameText,
                  tourist.birthdayText,
                  tourist.citizenshipText,
                  tourist.foreignPassportNumberText,
                  tourist.expirationDateOfForeignPassportText,
               )
            ) {
               isCorrect = false
            }
         } catch (_: Exception) {
         }
      }
      return isCorrect
   }



}