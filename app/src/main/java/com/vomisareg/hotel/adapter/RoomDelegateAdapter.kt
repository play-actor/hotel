package com.vomisareg.hotel.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.helper.widget.Flow
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.terrakok.cicerone.Router
import com.vomisareg.hotel.R
import com.vomisareg.hotel.adapter.base.ViewBindingDelegateAdapter
import com.vomisareg.hotel.adapter.base.ViewPagerAdapter
import com.vomisareg.hotel.databinding.RoomMainBinding
import com.vomisareg.hotel.di.ComponentManager
import com.vomisareg.hotel.navigation.Screens
import javax.inject.Inject


class RoomDelegateAdapter(val context: Context) :
   ViewBindingDelegateAdapter<RoomModelItem, RoomMainBinding>(
      RoomMainBinding::inflate
   ) {
   private var idIter: Int = 0

   @Inject
   lateinit var router: Router

   init {
      ComponentManager.instance.appComponent.inject(this)
   }

   override fun validate(): Boolean {
      return true
   }

   @SuppressLint("SetTextI18n")
   override fun RoomMainBinding.onBind(item: RoomModelItem) {
      val viewPagerAdapter = ViewPagerAdapter(context, item.imageUrlsList)
      viewPager.adapter = viewPagerAdapter
      layoutTab.setupWithViewPager(viewPager)
      val listTag = item.peculiarities.sortedByDescending { it.length }
      label.text = item.name
      priceForIt.text = item.pricePer
      minimalPrice.text = "от ${item.price} \u20BD"
      listTag.forEach { textTag ->
         val child = View.inflate(context, R.layout.tag_item, null)
         child.findViewById<TextView>(R.id.tv_tag).text = textTag

         val params = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
         )
         child.id = idIter
         child.layoutParams = params
         llForTag.addView(child)
         llForTag.findViewById<Flow>(R.id.flow).referencedIds =
            llForTag.findViewById<Flow>(R.id.flow).referencedIds.plus(
               llForTag.findViewById<ConstraintLayout>(idIter).id
            )
         idIter++
      }
      btnInBooking.setOnClickListener {
         router.navigateTo(Screens.bookingScreen())
      }
   }


   override fun isForViewType(item: Any) = item is RoomModelItem

   override fun RoomModelItem.getItemId(): Any = id
}