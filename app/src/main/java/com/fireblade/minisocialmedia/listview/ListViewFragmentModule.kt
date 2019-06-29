package com.fireblade.minisocialmedia.listview

import com.fireblade.minisocialmedia.network.IPlaceholderApiService
import dagger.Module
import dagger.Provides

@Module
class ListViewFragmentModule {

  @Provides
  fun provideListView(fragment: ListViewFragment): IListView = fragment

  @Provides
  fun provideListViewPresenter(fragment: ListViewFragment, placeholderApiService: IPlaceholderApiService) : IListPresenter =
    ListViewPresenter(fragment, placeholderApiService)
}