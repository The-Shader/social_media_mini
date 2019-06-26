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
  fun provideHomePresenter(fragment: ListViewFragment, requestService: IRequestService) : IListPresenter =
    ListViewPresenter(fragment, requestService)
}