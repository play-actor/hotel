package com.vomisareg.hotel.adapter

import android.annotation.SuppressLint
import android.content.Context
import com.vomisareg.delegateadapter.adapter.ViewBindingDelegateAdapter
import com.vomisareg.hotel.bus.BusEvent
import com.vomisareg.hotel.bus.EventHandler
import com.vomisareg.hotel.databinding.AddTouristItemBinding
import com.vomisareg.hotel.di.ComponentManager
import javax.inject.Inject

class AddTouristsDelegateAdapter(val context: Context) :
   ViewBindingDelegateAdapter<AddTouristsModelItem, AddTouristItemBinding>(
      AddTouristItemBinding::inflate
   ) {
   private var idItr: Int = 0

   init {
      ComponentManager.instance.appComponent.inject(this)
   }

   @Inject
   lateinit var eventHandler: EventHandler

   @SuppressLint("SetTextI18n")
   override fun AddTouristItemBinding.onBind(item: AddTouristsModelItem) {
      button.setOnClickListener {
         eventHandler.postEvent(BusEvent.Refresh)
      }
   }

   override fun isForViewType(item: Any) = item is AddTouristsModelItem

   override fun validate(): Boolean {
      return true
   }

   override fun AddTouristsModelItem.getItemId(): Any = id
}