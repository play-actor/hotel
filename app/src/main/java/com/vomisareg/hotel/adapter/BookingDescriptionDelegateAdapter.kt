package com.vomisareg.hotel.adapter

import android.annotation.SuppressLint
import android.content.Context
import com.vomisareg.delegateadapter.adapter.ViewBindingDelegateAdapter
import com.vomisareg.hotel.databinding.BookingDescriptionItemBinding


class BookingDescriptionDelegateAdapter(val context: Context) :
   ViewBindingDelegateAdapter<BookingDescriptionModelItem, BookingDescriptionItemBinding>(
      BookingDescriptionItemBinding::inflate
   ) {

   override fun validate(): Boolean {
      return true
   }

   @SuppressLint("SetTextI18n")
   override fun BookingDescriptionItemBinding.onBind(item: BookingDescriptionModelItem) {
      addressHotelTitle.text = item.arrival_country
      dateTitle.text = "${item.tour_date_start} - ${item.tour_date_stop}"
      cateringAtTheHotelTitle.text = item.nutrition
      flyForTitle.text = item.departure
      countNightTitle.text = item.number_of_nights.toString()
      hotelTitle.text = item.name
      roomTitle.text = item.room
   }


   override fun isForViewType(item: Any) = item is BookingDescriptionModelItem

   override fun BookingDescriptionModelItem.getItemId(): Any = name
}