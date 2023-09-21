package com.vomisareg.delegateadapter.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vomisareg.hotel.ui.rooms.VolidateProvider


open class CompositeDelegateAdapter(vararg adapters: DelegateAdapter) : VolidateProvider,
   RecyclerView.Adapter<RecyclerView.ViewHolder>() {

   protected open var adapterState = AdaptersState(adapters.toList())

   override fun validate(): Boolean {
      var answer: Boolean = true
      adapterState.data.forEachIndexed { index, any ->
         if (!adapterState.getAdapterByItemPosition(index).validate()) answer = false
      }
      return answer
   }

   override fun getItemViewType(itemPosition: Int): Int =
      adapterState.getAdapterPosition(itemPosition)

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
      adapterState.getAdapter(viewType).onCreateViewHolder(parent, viewType)

   override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
      adapterState.getAdapter(getItemViewType(position))
         .onBindViewHolder(holder, adapterState.data, position)

   override fun onViewRecycled(holder: RecyclerView.ViewHolder) =
      adapterState.getAdapter(holder.itemViewType).onRecycled(holder)

   open fun swapData(data: List<Any>) {
      val newAdapterState = adapterState.copy(data = data)
      val diffCallback = DiffUtilCallback(adapterState, newAdapterState)
      val diffResult = DiffUtil.calculateDiff(diffCallback)
      adapterState = newAdapterState
      diffResult.dispatchUpdatesTo(this)
   }

   override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
      adapterState.getAdapter(holder.itemViewType).onAttachedToWindow(holder)
   }

   override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
      adapterState.getAdapter(holder.itemViewType).onDetachedFromWindow(holder)
   }

   override fun getItemCount(): Int = adapterState.data.size
}