package com.fireblade.minisocialmedia.di

import android.app.Application
import com.fireblade.minisocialmedia.home.HomeActivityProvider
import com.fireblade.minisocialmedia.MiniSocialMediaApplication
import com.fireblade.detail.di.DetailedPostActivityProvider
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules =
[AndroidInjectionModule::class,
  ApplicationModule::class,
  HomeActivityProvider::class,
  DetailedPostActivityProvider::class])
interface AppComponent : AndroidInjector<MiniSocialMediaApplication> {

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance application: Application): AppComponent
  }

  override fun inject(app: MiniSocialMediaApplication)
}
