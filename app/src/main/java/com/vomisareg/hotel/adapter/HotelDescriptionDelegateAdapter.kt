package com.vomisareg.hotel.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.helper.widget.Flow
import androidx.constraintlayout.widget.ConstraintLayout
import com.vomisareg.hotel.R
import com.vomisareg.hotel.databinding.HotelDescriptionItemBinding


class HotelDescriptionDelegateAdapter(val context: Context) :
   ViewBindingDelegateAdapter<HotelDescriptionItem, HotelDescriptionItemBinding>(
      HotelDescriptionItemBinding::inflate
   ) {
   private var idIter: Int = 0

   override fun HotelDescriptionItemBinding.onBind(item: HotelDescriptionItem) {
      tvDescription.text = item.description
      val listTag = item.tagsText.sortedByDescending { it.length }
      listTag.forEach { textTag ->
         val child = View.inflate(context, R.layout.tag_item, null)
         child.findViewById<TextView>(R.id.tv_tag).text = textTag
         child.id = idIter
         val params = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
         )
         child.layoutParams = params
         llForTag.addView(child)
         llForTag.findViewById<Flow>(R.id.flow).referencedIds =
            llForTag.findViewById<Flow>(R.id.flow).referencedIds.plus(
               llForTag.findViewById<ConstraintLayout>(idIter).id
            )
         idIter = idIter.inc()
      }
      setFunctionHotelView(
         funHotels.findViewById(R.id.facilities),
         R.drawable.emoji_happy,
         context.getString(R.string.facilities_label),
         context.getString(R.string.function_description)
      )
      setFunctionHotelView(
         funHotels.findViewById(R.id.included),
         R.drawable.tick_square,
         context.getString(R.string.included_label),
         context.getString(R.string.function_description)
      )
      setFunctionHotelView(
         funHotels.findViewById(R.id.not_included),
         R.drawable.close_square,
         context.getString(R.string.not_included_label),
         context.getString(R.string.function_description)
      )
   }

   private fun setFunctionHotelView(
      constraintLayout: ConstraintLayout,
      resId: Int,
      label: String,
      description: String,
   ) {
      constraintLayout.apply {
         findViewById<ImageView>(R.id.icon).setImageResource(resId)
         findViewById<TextView>(R.id.tv_label).text = label
         findViewById<TextView>(R.id.tv_description_hotel).text = description
      }
   }

   override fun isForViewType(item: Any) = item is HotelDescriptionItem

   override fun HotelDescriptionItem.getItemId(): Any = description
}