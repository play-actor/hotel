package com.vomisareg.hotel.adapter


data class HotelMainItem(
   var id: Int,
   var name: String,
   var address: String,
   var minimal_price: Int,
   var price_for_it: String,
   var rating: Int,
   var rating_name: String,
   var image_list: List<String>,
)

data class HotelDescriptionItem(
   val tagsText: List<String>,
   val description: String,
)

data class RoomModelItem(
   var id: Int,
   var name: String,
   var price: Int,
   var pricePer: String,
   var peculiarities: List<String>,
   var imageUrlsList: List<String>,
)

data class BookingMainModelItem(
   var id: Int,
   var name: String,
   var address: String,
   var rating: Int,
   var rating_name: String,
)

data class BookingDescriptionModelItem(
   var name: String,
   var departure: String,
   var arrival_country: String,
   var tour_date_start: String,
   var tour_date_stop: String,
   var number_of_nights: Int,
   var room: String,
   var nutrition: String,
)

data class BuyerInfoModelItem(var id: Int)

data class TouristsModelItem(var id: Int)

data class PriceModelItem(
   var id: Int,
   var tour_price: Int,
   var fuel_charge: Int,
   var service_charge: Int,
   val function: () -> Unit,
)

data class AddTouristsModelItem(var id: Int)