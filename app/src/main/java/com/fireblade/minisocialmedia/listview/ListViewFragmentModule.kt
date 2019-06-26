package com.fireblade.minisocialmedia.listview

import com.fireblade.minisocialmedia.network.IRequestService
import dagger.Module
import dagger.Provides

@Module
class ListViewFragmentModule {

  @Provides
  fun provideHomeView(fragment: ListViewFragment): IListView = fragment

  @Provides
  fun provideRequestInterface() : IRequestService = IRequestService.create()

  @Provides
  fun provideListViewPresenter(fragment: ListViewFragment, requestService: IRequestService) : IListPresenter =
    ListViewPresenter(fragment, requestService)
}