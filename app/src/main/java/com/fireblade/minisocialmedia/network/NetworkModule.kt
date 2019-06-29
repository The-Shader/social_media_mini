package com.fireblade.minisocialmedia.network

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

  @Provides
  fun provideRequestService(placeholderApiSdk: PlaceholderApiSdk) : IPlaceholderApiService = IPlaceholderApiService.create(placeholderApiSdk)

  @Provides
  fun provideRequestClient(httpClient: OkHttpClient, rxJava2CallAdapterFactory: RxJava2CallAdapterFactory, gsonConverterFactory: GsonConverterFactory) =
    PlaceholderApiSdk(httpClient, rxJava2CallAdapterFactory, gsonConverterFactory)

  @Provides
  fun provideOkHttpClient(context: Context) = HttpClientBuilder(context).build()

  @Provides
  fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

  @Provides
  fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()
}