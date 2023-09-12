package com.vomisareg.hotel.adapter

import android.annotation.SuppressLint
import android.content.Context
import com.vomisareg.hotel.adapter.base.ViewBindingDelegateAdapter
import com.vomisareg.hotel.adapter.base.ViewPagerAdapter
import com.vomisareg.hotel.databinding.HotelMainBinding


class HotelMainDelegateAdapter(val context: Context) :
   ViewBindingDelegateAdapter<HotelMainItem, HotelMainBinding>(
      HotelMainBinding::inflate
   ) {
   override fun validate(): Boolean {
      return true
   }

   @SuppressLint("SetTextI18n")
   override fun HotelMainBinding.onBind(item: HotelMainItem) {
      val viewPagerAdapter = ViewPagerAdapter(context, item.image_list)
      viewPager.adapter = viewPagerAdapter
      layoutTab.setupWithViewPager(viewPager)
      rating.tvDescriptionHotel.text = "${item.rating} ${item.rating_name}"
      label.text = item.name
      adress.text = item.address
      priceForIt.text = item.price_for_it
      minimalPrice.text = "от ${item.minimal_price} \u20BD"
   }


   override fun isForViewType(item: Any) = item is HotelMainItem

   override fun HotelMainItem.getItemId(): Any = id
}