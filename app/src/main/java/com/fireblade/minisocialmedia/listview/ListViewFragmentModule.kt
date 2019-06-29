package com.fireblade.minisocialmedia.listview

import com.fireblade.minisocialmedia.persistence.SocialMediaRepository
import dagger.Module
import dagger.Provides

@Module
class ListViewFragmentModule {

  @Provides
  fun provideListView(fragment: ListViewFragment): IListView = fragment

  @Provides
  fun provideListViewPresenter(fragment: ListViewFragment, socialMediaRepository: SocialMediaRepository) : IListPresenter =
    ListViewPresenter(fragment, socialMediaRepository)
}