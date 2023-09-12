package com.vomisareg.hotel.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.telephony.PhoneNumberFormattingTextWatcher
import android.telephony.PhoneNumberUtils
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import com.vomisareg.hotel.R
import com.vomisareg.hotel.adapter.base.ViewBindingDelegateAdapter
import com.vomisareg.hotel.databinding.BuyerInfoBinding
import com.vomisareg.hotel.util.isNull


class BuyerInfoDelegateAdapter(val context: Context) :
   ViewBindingDelegateAdapter<BuyerInfoModelItem, BuyerInfoBinding>(
      BuyerInfoBinding::inflate
   ) {

   override fun validate(): Boolean {
      return true
   }
   @SuppressLint("SetTextI18n")
   override fun BuyerInfoBinding.onBind(item: BuyerInfoModelItem) {
      emailText.addTextChangedListener(object : TextWatcher {
         override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
         }

         override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

         override fun afterTextChanged(s: Editable?) {
            val email = s.toString().trim()
            if (TextUtils.isEmpty(email) || !isValidEmail(email)) {
               emailText.setBackgroundColor(context.resources.getColor(R.color.error))
            }
         }
      })
      bayerPhoneNumberText.setOnFocusChangeListener { v, hasFocus ->
         if(hasFocus){
         if (bayerPhoneNumberText.text?.isEmpty() == true) {
            bayerPhoneNumberText.hint = "+7 (***) ***-**-**"
         }}else{
            bayerPhoneNumberText.hint=""
         }
      }
      bayerPhoneNumberText.addTextChangedListener(object : PhoneNumberFormattingTextWatcher("RU") {
         override fun afterTextChanged(s: Editable?) {
            s?.apply {
               if (this.length < 2) {
                  this.replace(0, this.length, "+7$s")
               }
            }
            super.afterTextChanged(s)
            val phoneNumber = s.toString().trim()
               .replace("(", "")
               .replace(")", "")
               .replace("-", "")
               .replace("+", "")
               .replace(" ", "")
            if (!phoneNumber.isNull() && phoneNumber.isNotEmpty() &&
               (TextUtils.isEmpty(phoneNumber) || !isValidPhoneNumber(phoneNumber))
            ) {
               bayerPhoneNumberText.setBackgroundColor(context.resources.getColor(R.color.error))
            } else {
               bayerPhoneNumber.error = null
            }

         }
      })
   }


   private fun isValidPhoneNumber(phoneNumber: String): Boolean {
      return PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)
   }

   private fun isValidEmail(email: String): Boolean {
      return Patterns.EMAIL_ADDRESS.matcher(email).matches()
   }

   override fun isForViewType(item: Any) = item is BuyerInfoModelItem

   override fun BuyerInfoModelItem.getItemId(): Any = id
}