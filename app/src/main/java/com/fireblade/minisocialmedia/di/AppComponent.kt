package com.fireblade.minisocialmedia.di

import android.app.Application
import com.fireblade.minisocialmedia.home.HomeActivityProvider
import com.fireblade.minisocialmedia.MiniSocialMediaApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules =
[AndroidSupportInjectionModule::class,
  HomeActivityProvider::class])
interface AppComponent {

  fun inject(app: MiniSocialMediaApplication)

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent
  }
}