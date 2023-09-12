package com.vomisareg.hotel.ui.rooms

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vomisareg.hotel.R
import com.vomisareg.hotel.adapter.base.CompositeDelegateAdapter
import com.vomisareg.hotel.adapter.RoomDelegateAdapter
import com.vomisareg.hotel.adapter.RoomModelItem
import com.vomisareg.hotel.databinding.FragmentRoomBinding

class RoomsFragment : Fragment() {
   private var viewModel: RoomsViewModel? = null
   private var _binding: FragmentRoomBinding? = null
   private val binding get() = _binding!!

   private val comAdapter = lazy {
      context?.let {
         CompositeDelegateAdapter(
            RoomDelegateAdapter(it),
         )
      }
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      arguments?.getString("hotel_name", getString(R.string.hotel_title))
      this.viewModel = ViewModelProvider(this)[RoomsViewModel::class.java]
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?,
   ): View {
      _binding = FragmentRoomBinding.inflate(inflater, container, false)
      val view = binding.root
      binding.apply {
         this.toolbarTitle.apply {
            text =
               arguments?.getString("hotel_name", getString(R.string.hotel_title))
            gravity = Gravity.CENTER
         }
         this.toolbarMain.setNavigationOnClickListener { viewModel?.back() }
         viewModel?.mutableLiveData?.observe(viewLifecycleOwner) { vm ->
            val modelList: MutableList<Any> = mutableListOf()
            vm.rooms.forEach {
               modelList.add(
                  RoomModelItem(
                     it.id,
                     it.name,
                     it.price,
                     it.price_per,
                     it.peculiarities,
                     it.image_urls,
                  )
               )
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