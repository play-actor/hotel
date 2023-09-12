package com.vomisareg.hotel.adapter.base

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso
import com.vomisareg.hotel.util.RoundedCornersTransformation

class ViewPagerAdapter(val context: Context, private val imageUrls: List<String?>) :
   PagerAdapter() {


   override fun getCount(): Int {
      return imageUrls.size
   }

   override fun isViewFromObject(view: View, obj: Any): Boolean {
      return view === obj
   }

   override fun instantiateItem(container: ViewGroup, position: Int): Any {
      val imageView = ImageView(context)
      Picasso.get()
         .load(imageUrls[position])
         .fit()
         .centerCrop()
         .transform(RoundedCornersTransformation(15))
         .into(imageView)
      container.addView(imageView)
      return imageView
   }

   override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
      container.removeView(obj as View)
   }

}