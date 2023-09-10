package com.vomisareg.hotel.model

data class RoomModel(
   var id: Int = Int.MIN_VALUE,
   var name: String = "",
   var price: Int = Int.MIN_VALUE,
   var price_per: String = "",
   var peculiarities: List<String> = ArrayList(),
   var image_urls: List<String> = ArrayList(),
)