package com.vomisareg.hotel.di.module

import android.app.Application
import com.google.gson.Gson
import com.vomisareg.hotel.retrofit.HotelServerApi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
   private val baseUrl: String = "https://run.mocky.io/v3/"

   @Provides
   @Singleton
   fun providesNetworkService(retrofit: Retrofit): HotelServerApi {
      return retrofit.create(HotelServerApi::class.java)
   }

   @Provides
   @Singleton
   internal fun providesRetrofit(): Retrofit {
      return Retrofit.Builder()
         .addConverterFactory(GsonConverterFactory.create())
         .baseUrl(baseUrl)
         .build()
   }
}