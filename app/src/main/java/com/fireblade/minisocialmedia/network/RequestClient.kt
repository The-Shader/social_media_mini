package com.fireblade.minisocialmedia.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RequestClient {

  companion object {

    private lateinit var retrofit: Retrofit

    fun getClient() : Retrofit {
      val interceptor =  HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)

      val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

      retrofit = Retrofit.Builder()
        .baseUrl("http://jsonplaceholder.typicode.com")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

      return retrofit
    }
  }
}