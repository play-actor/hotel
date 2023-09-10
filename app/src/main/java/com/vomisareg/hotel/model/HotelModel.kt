package com.vomisareg.hotel.model

data class HotelModel(
   var id: Int = Int.MIN_VALUE,
   var name: String = "",
   var adress: String = "",
   var minimal_price: Int = Int.MIN_VALUE,
   var price_for_it: String = "",
   var rating: Int = Int.MIN_VALUE,
   var rating_name: String = "",
   var image_urls: List<String> = ArrayList(),
   var about_the_hotel: AboutTheHotel = AboutTheHotel(),
) {
}