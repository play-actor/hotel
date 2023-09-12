package com.vomisareg.hotel.ui.hotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vomisareg.hotel.adapter.base.CompositeDelegateAdapter
import com.vomisareg.hotel.adapter.HotelDescriptionDelegateAdapter
import com.vomisareg.hotel.adapter.HotelDescriptionItem
import com.vomisareg.hotel.adapter.HotelMainDelegateAdapter
import com.vomisareg.hotel.adapter.HotelMainItem
import com.vomisareg.hotel.databinding.FragmentHotelBinding


class HotelFragment : Fragment() {

   private var viewModel: HotelViewModel? = null
   private var _binding: FragmentHotelBinding? = null
   private val binding get() = _binding!!

   private val comAdapter = lazy {
      context?.let {
         CompositeDelegateAdapter(
            HotelMainDelegateAdapter(it),
            HotelDescriptionDelegateAdapter(it)
         )
      }
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      this.viewModel = ViewModelProvider(this)[HotelViewModel::class.java]
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?,
   ): View {
      _binding = FragmentHotelBinding.inflate(inflater, container, false)
      val view = binding.root
      binding.apply {
         viewModel?.hotelModelMutableLiveData?.observe(viewLifecycleOwner) { vm ->
            val modelList: List<Any> = mutableListOf(
               HotelMainItem(
                  vm.id,
                  vm.name,
                  vm.adress,
                  vm.minimal_price,
                  vm.price_for_it,
                  vm.rating,
                  vm.rating_name,
                  vm.image_urls,
               ),
               HotelDescriptionItem(
                  vm.about_the_hotel.peculiarities,
                  vm.about_the_hotel.description
               )
            )
            this.btnInRoom.setOnClickListener {
               viewModel?.openRoom(vm.name)
            }
            this.rv.apply {
               layoutManager = LinearLayoutManager(context)
               adapter = comAdapter.value
               comAdapter.value?.swapData(modelList)
            }

         }
      }
      return view
   }

}