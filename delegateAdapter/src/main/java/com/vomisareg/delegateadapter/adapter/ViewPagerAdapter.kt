package com.vomisareg.delegateadapter.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter


class ViewPagerAdapter(
   val context: Context,
   private val count: Int,
   private val advancedFunctionForAdapter: (imageView: ImageView, position: Int) -> View,
) :
   PagerAdapter() {


   override fun getCount(): Int {
      return count
   }

   override fun isViewFromObject(view: View, obj: Any): Boolean {
      return view === obj
   }

   override fun instantiateItem(container: ViewGroup, position: Int): Any {
      val imageView = ImageView(context)
      val view = advancedFunctionForAdapter.invoke(imageView, position)
      container.addView(view)
      return imageView
   }

   override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
      container.removeView(obj as View)
   }

}