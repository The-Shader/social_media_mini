package com.fireblade.feed

import com.fireblade.persistence.SocialMediaRepository
import dagger.Module
import dagger.Provides

@Module
class FeedFragmentModule {

  @Provides
  fun provideListView(fragment: FeedFragment): IFeedView = fragment

  @Provides
  fun provideListViewPresenter(fragment: FeedFragment, socialMediaRepository: SocialMediaRepository) : IFeedPresenter =
    FeedPresenter(fragment, socialMediaRepository)
}