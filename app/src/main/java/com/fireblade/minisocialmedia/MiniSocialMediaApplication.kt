package com.fireblade.minisocialmedia

import android.app.Activity
import android.app.Application
import com.fireblade.minisocialmedia.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MiniSocialMediaApplication : Application(), HasActivityInjector {

  @Inject
  lateinit var activityDispatcher: DispatchingAndroidInjector<Activity>

  override fun activityInjector(): AndroidInjector<Activity> = activityDispatcher

  override fun onCreate() {
    super.onCreate()

    DaggerAppComponent
      .builder()
      .application(this)
      .build()
      .inject(this)
  }
}