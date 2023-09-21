package com.vomisareg.hotel.adapter

import android.annotation.SuppressLint
import android.content.Context
import com.squareup.picasso.Picasso
import com.vomisareg.delegateadapter.adapter.ViewBindingDelegateAdapter
import com.vomisareg.delegateadapter.adapter.ViewPagerAdapter
import com.vomisareg.hotel.databinding.HotelMainBinding
import com.vomisareg.hotel.di.ComponentManager
import com.vomisareg.hotel.di.module.ImageModule
import com.vomisareg.hotel.util.RoundedCornersTransformation
import javax.inject.Inject


class HotelMainDelegateAdapter(val context: Context) :
   ViewBindingDelegateAdapter<HotelMainItem, HotelMainBinding>(
      HotelMainBinding::inflate
   ) {

   init {
      ComponentManager.instance.appComponent.inject(this)
   }

   @Inject
   lateinit var imageModule: ImageModule
   override fun validate(): Boolean {
      return true
   }

   @SuppressLint("SetTextI18n")
   override fun HotelMainBinding.onBind(item: HotelMainItem) {
      val viewPagerAdapter =
         ViewPagerAdapter(context, item.image_list.size) { imageView, position ->
            imageModule.showImage(imageView,item.image_list,position)
         }
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