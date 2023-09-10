package com.vomisareg.hotel.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

class Util {
}
fun <T> T.isNull(): Boolean {
   return this==null
}

fun <T> T.isNotNull(): Boolean {
   return this!=null
}

fun <T> LiveData<T>.toMutableLiveData(): MutableLiveData<T> {
   val mediatorLiveData = MediatorLiveData<T>()
   mediatorLiveData.addSource(this) {
      mediatorLiveData.value = it
   }
   return mediatorLiveData
}

