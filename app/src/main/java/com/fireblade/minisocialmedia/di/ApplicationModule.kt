package com.fireblade.minisocialmedia.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

  @Provides
  @Singleton
  fun provideApplicationContext(application: Application): Context {
    return application.applicationContext
  }
}