package com.vomisareg.hotel.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import com.google.android.material.textfield.TextInputEditText
import com.vomisareg.hotel.R
import com.vomisareg.hotel.adapter.base.ViewBindingDelegateAdapter
import com.vomisareg.hotel.bus.EventHandler
import com.vomisareg.hotel.databinding.TouristItemBinding
import com.vomisareg.hotel.di.ComponentManager
import com.vomisareg.hotel.util.NumberToWords
import java.util.Locale
import javax.inject.Inject

class TouristsDelegateAdapter(val context: Context) :
   ViewBindingDelegateAdapter<TouristsModelItem, TouristItemBinding>(
      TouristItemBinding::inflate
   ) {

   init {
      ComponentManager.instance.appComponent.inject(this)
   }

   @Inject
   lateinit var eventHandler: EventHandler
   private var binding: MutableList<TouristItemBinding> = mutableListOf()

   @SuppressLint("SetTextI18n")
   override fun TouristItemBinding.onBind(item: TouristsModelItem) {
      binding.add(item.id, this)
      label.text = "${
         NumberToWords.convert(item.id + 1)
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
      } турист"
      if (item.id > 0) {
         allData.visibility = View.GONE
      }
      button.setOnClickListener {
         allData.visibility = if (allData.visibility == View.GONE) {
            View.VISIBLE
         } else {
            View.GONE
         }
      }
   }

   override fun isForViewType(item: Any) = item is TouristsModelItem
   override fun validate(): Boolean {
      var isCorrect = true
      binding.forEach {
      if (!validateText(
            it.firstNameText,
            it.lastNameText,
            it.birthdayText,
            it.citizenshipText,
            it.foreignPassportNumberText,
            it.expirationDateOfForeignPassportText,
         )
      ) {
         isCorrect = false
      }}
      return isCorrect
   }


   private fun validateText(vararg fields: TextInputEditText): Boolean {
      var isCorrect = true
      fields.forEach { field ->
         if (field.text.isNullOrEmpty()) {
            field.setBackgroundColor(context.getColor(R.color.error))
            isCorrect = false
         }
      }
      return isCorrect
   }

   override fun TouristsModelItem.getItemId(): Any = id
}