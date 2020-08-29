package com.fireblade.minisocialmedia

import android.app.Application
import com.fireblade.minisocialmedia.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MiniSocialMediaApplication : Application(), HasAndroidInjector {

  @Inject
  lateinit var androidInjector: DispatchingAndroidInjector<Any>

  override fun androidInjector(): DispatchingAndroidInjector<Any> = androidInjector

  override fun onCreate() {
    super.onCreate()

    DaggerAppComponent
      .factory()
      .create(this)
      .inject(this)
  }
}