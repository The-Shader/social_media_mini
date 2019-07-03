package com.fireblade.detail

import com.fireblade.core.schedulers.ISchedulers
import com.fireblade.persistence.SocialMediaRepository
import dagger.Module
import dagger.Provides

@Module
class DetailedPostActivityModule {

  @Provides
  fun provideDetailedPostView(activity: DetailedPostActivity) : IDetailedPostView = activity

  @Provides
  fun provideDetailedPresenter(view: IDetailedPostView, socialMediaRepository: SocialMediaRepository, schedulers: ISchedulers) =
    DetailedPostPresenter(view, socialMediaRepository, schedulers)
}