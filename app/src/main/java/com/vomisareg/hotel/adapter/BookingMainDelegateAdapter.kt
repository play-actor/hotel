package com.vomisareg.hotel.adapter

import android.annotation.SuppressLint
import android.content.Context
import com.vomisareg.hotel.databinding.BookingMainBinding


class BookingMainDelegateAdapter(val context: Context) :
   ViewBindingDelegateAdapter<BookingMainModelItem, BookingMainBinding>(
      BookingMainBinding::inflate
   ) {



   @SuppressLint("SetTextI18n")
   override fun BookingMainBinding.onBind(item: BookingMainModelItem) {
      label.text =item.name
      address.text =item.address
      rating.tvDescriptionHotel.text = "${item.rating} ${item.rating_name}"
   }


   override fun isForViewType(item: Any) = item is BookingMainModelItem

   override fun BookingMainModelItem.getItemId(): Any = id
}