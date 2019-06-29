package com.fireblade.minisocialmedia.comments

import com.fireblade.minisocialmedia.network.IPlaceholderApiService
import dagger.Module
import dagger.Provides

@Module
class CommentViewFragmentModule {

  @Provides
  fun provideCommentView(fragment: CommentViewFragment): ICommentView = fragment

  @Provides
  fun provideSettingsEventHandler(fragment: CommentViewFragment) : ICommentEvents = fragment.activity as ICommentEvents

  @Provides
  fun provideCommentPresenter(fragment: CommentViewFragment, requestService: IPlaceholderApiService, events: ICommentEvents): ICommentPresenter =
      CommentPresenter(fragment, requestService, events)
}