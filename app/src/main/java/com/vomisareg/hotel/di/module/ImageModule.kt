package com.vomisareg.hotel.di.module

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.vomisareg.hotel.util.RoundedCornersTransformation
import dagger.Module
import javax.inject.Inject

@Module
class ImageModule @Inject constructor() {

   @Inject
   lateinit var context: Context

   fun showImage(imageView: ImageView, imageUrls: List<String?>, position: Int): ImageView {
      Picasso.get()
         .load(imageUrls[position])
         .fit()
         .centerCrop()
         .transform(RoundedCornersTransformation(15))
         .into(imageView)
      return imageView
   }
}