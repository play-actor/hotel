package com.vomisareg.delegateadapter.adapter

data class AdaptersState(
   private val adapters: List<DelegateAdapter>,
   val data: List<Any> = emptyList(),
) {

   private lateinit var adapterPositionsCache: Array<Int>

   fun getAdapterPosition(itemPosition: Int): Int {
      adapterPositionsCache = Array<Int>(data.size + 1) { -1 }
      val o = adapterPositionsCache[itemPosition].takeIf { it != -1 }
         ?: adapters.indexOfFirst { it.isForViewType(data, itemPosition) }
            .takeIf { it != -1 }
            ?.also { adapterPositionsCache[itemPosition] = it }
         ?: error("Provide adapter for type ${data[itemPosition].javaClass} at position: $itemPosition")
      return o
   }

   fun getAdapter(adapterPosition: Int): DelegateAdapter = adapters[adapterPosition]

   fun getAdapterByItemPosition(itemPosition: Int): DelegateAdapter =
      adapters[getAdapterPosition(itemPosition)]

   fun getDelegateAdapters(): List<DelegateAdapter> {
      return adapters
   }
}
