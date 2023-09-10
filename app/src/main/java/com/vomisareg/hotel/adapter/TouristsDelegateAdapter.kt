package com.vomisareg.hotel.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import com.vomisareg.hotel.bus.EventHandler
import com.vomisareg.hotel.databinding.TouristItemBinding
import com.vomisareg.hotel.di.ComponentManager
import com.vomisareg.hotel.util.NumberToWords
import java.util.Locale
import javax.inject.Inject

class TouristsDelegateAdapter(val context: Context) :
   ViewBindingDelegateAdapter<TouristsModelItem, TouristItemBinding>(
      TouristItemBinding::inflate
   ) {

   init {
      ComponentManager.instance.appComponent.inject(this)
   }

   @Inject
   lateinit var eventHandler: EventHandler

   @SuppressLint("SetTextI18n")
   override fun TouristItemBinding.onBind(item: TouristsModelItem) {
      label.text = "${
         NumberToWords.convert(item.id + 1)
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
      } турист"
      if (item.id > 0) {
         allData.visibility = View.GONE
      }
      button.setOnClickListener {
         allData.visibility = if (allData.visibility == View.GONE) {
            View.VISIBLE
         } else {
            View.GONE
         }
      }
   }

   override fun isForViewType(item: Any) = item is TouristsModelItem

   override fun TouristsModelItem.getItemId(): Any = 10 + id
}