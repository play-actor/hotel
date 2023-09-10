package com.vomisareg.hotel.model

data class BookingRoomModel(
   var id: Int = Int.MIN_VALUE,
   var hotel_name: String = "",
   var hotel_adress: String = "",
   var horating: Int = Int.MIN_VALUE,
   var rating_name: String = "",
   var departure: String = "",
   var arrival_country: String = "",
   var tour_date_start: String = "",
   var tour_date_stop: String = "",
   var number_of_nights: Int = Int.MIN_VALUE,
   var room: String = "",
   var nutrition: String = "",
   var tour_price: Int = Int.MIN_VALUE,
   var fuel_charge: Int = Int.MIN_VALUE,
   var service_charge: Int = Int.MIN_VALUE,
)