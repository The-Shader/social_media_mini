package com.fireblade.detail

import com.fireblade.persistence.SocialMediaRepository
import dagger.Module
import dagger.Provides

@Module
class DetailedPostActivityModule {

  @Provides
  fun provideDetailedPostView(activity: DetailedPostActivity) : IDetailedPostView = activity

  @Provides
  fun provideDetailedPresenter(view: IDetailedPostView, socialMediaRepository: SocialMediaRepository) =
    DetailedPostPresenter(view, socialMediaRepository)
}