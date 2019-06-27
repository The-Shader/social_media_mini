package com.fireblade.minisocialmedia.comments

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CommentViewFragmentProvider {
  @ContributesAndroidInjector(modules = [CommentViewFragmentModule::class])
  abstract fun bindCommentViewFragment(): CommentViewFragment
}