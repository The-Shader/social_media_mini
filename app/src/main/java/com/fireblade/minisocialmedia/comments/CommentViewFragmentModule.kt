package com.fireblade.minisocialmedia.comments

import com.fireblade.minisocialmedia.persistence.SocialMediaRepository
import dagger.Module
import dagger.Provides

@Module
class CommentViewFragmentModule {

  @Provides
  fun provideCommentView(fragment: CommentViewFragment): ICommentView = fragment

  @Provides
  fun provideSettingsEventHandler(fragment: CommentViewFragment) : ICommentEvents = fragment.activity as ICommentEvents

  @Provides
  fun provideCommentPresenter(fragment: CommentViewFragment, socialMediaRepository: SocialMediaRepository, events: ICommentEvents): ICommentPresenter =
      CommentPresenter(fragment, socialMediaRepository, events)
}