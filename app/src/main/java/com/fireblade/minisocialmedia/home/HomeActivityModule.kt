package com.fireblade.minisocialmedia.home

import dagger.Module
import dagger.Provides

@Module
class HomeActivityModule {

  @Provides
  fun provideHomePresenter(activity: HomeActivity) : IHomePresenter =
    HomePresenter(activity)
}