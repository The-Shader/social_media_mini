package com.fireblade.minisocialmedia.details

import dagger.Module
import dagger.Provides

@Module
class DetailedPostActivityModule {

  @Provides
  fun provideDetailedPostActivity(activity: DetailedPostActivity) : DetailedPostActivity = activity
}