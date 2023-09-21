package com.vomisareg.hotel.adapter

import android.annotation.SuppressLint
import android.content.Context
import com.vomisareg.delegateadapter.adapter.ViewBindingDelegateAdapter
import com.vomisareg.hotel.bus.EventHandler
import com.vomisareg.hotel.databinding.PriceBlockItemBinding
import com.vomisareg.hotel.di.ComponentManager
import javax.inject.Inject

class PriceDelegateAdapter(val context: Context) :
   ViewBindingDelegateAdapter<PriceModelItem, PriceBlockItemBinding>(
      PriceBlockItemBinding::inflate
   ) {
   init {
      ComponentManager.instance.appComponent.inject(this)
   }

   override fun validate(): Boolean {
      return true
   }

   @Inject
   lateinit var eventHandler: EventHandler

   @SuppressLint("SetTextI18n")
   override fun PriceBlockItemBinding.onBind(item: PriceModelItem) {
      priceBlock.tourPriceTitle.text = item.tour_price.toString()
      priceBlock.fuelChargeTitle.text = item.fuel_charge.toString()
      priceBlock.serviceChargeTitle.text = item.service_charge.toString()
      val finalPrice = (item.tour_price + item.fuel_charge + item.service_charge).toString()
      priceBlock.finalPriceTitle.text = finalPrice
      payButton.text = "Оплатить $finalPrice ₽"
      payButton.setOnClickListener {
         item.function.invoke()
      }
   }

   override fun isForViewType(item: Any) = item is PriceModelItem

   override fun PriceModelItem.getItemId(): Any = 1
}
