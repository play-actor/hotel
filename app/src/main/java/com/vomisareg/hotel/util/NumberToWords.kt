package com.vomisareg.hotel.util

import java.text.DecimalFormat

class NumberToWords {
   companion object {
      private val tensNames = arrayOf(
         "", " десятый", " Двадцатый", " Тридцатый", " Сороковой",
         " Пятьдесятый", " Шестьдесятый", " Семдесятый", " Восемдесятый", " Девяностый"
      )

      private val numNames = arrayOf(
         "",
         " первый",
         " второй",
         " третий",
         " четвертый",
         " пятый",
         " шестой",
         " седьмой",
         " восьмой",
         " девятый",
         " десятый",
         " одинадцатый",
         " двенадцатый",
         " тринадцвтый",
         " четырнадцатый",
         " пятнадцатый",
         " шеснадцатый",
         " семнадцатый",
         " восемнадцатый",
         " девятнадцатый"
      )

      private fun convertLessThanOneThousand(number: Int): String {
         var finalNumber = number
         var soFar: String
         if (finalNumber % 100 < 20) {
            soFar = numNames[finalNumber % 100]
            finalNumber /= 100
         } else {
            soFar = numNames[finalNumber % 10]
            finalNumber /= 10
            soFar = tensNames[finalNumber % 10] + soFar
            finalNumber /= 10
         }
         return if (finalNumber == 0) soFar else numNames[finalNumber] + " сто" + soFar
      }

      fun convert(number: Int): String {
         if (number == 0) {
            return "ноль"
         }
         var snumber: String = number.toString()

         val mask = "000000000000"
         val df = DecimalFormat(mask)
         snumber = df.format(number)

         // XXXnnnnnnnnn
         val billions = snumber.substring(0, 3).toInt()
         // nnnXXXnnnnnn
         val millions = snumber.substring(3, 6).toInt()
         // nnnnnnXXXnnn
         val hundredThousands = snumber.substring(6, 9).toInt()
         // nnnnnnnnnXXX
         val thousands = snumber.substring(9, 12).toInt()
         val tradBillions: String = when (billions) {
            0 -> ""
            1 -> convertLessThanOneThousand(billions) + " миллиард "
            else -> convertLessThanOneThousand(billions) + " миллиард "
         }
         var result = tradBillions
         val tradMillions: String = when (millions) {
            0 -> ""
            1 -> convertLessThanOneThousand(millions) + " миллион "
            else -> convertLessThanOneThousand(millions) + " миллион "
         }
         result += tradMillions
         val tradHundredThousands: String = when (hundredThousands) {
            0 -> ""
            1 -> "одна тысяча "
            else -> convertLessThanOneThousand(hundredThousands) + " тысяч "
         }
         result += tradHundredThousands
         val tradThousand: String = convertLessThanOneThousand(thousands)
         result += tradThousand

         return result.replace("^\\s+".toRegex(), "").replace("\\b\\s{2,}\\b".toRegex(), " ")
      }
   }
}