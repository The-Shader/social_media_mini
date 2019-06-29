package com.fireblade.minisocialmedia.comments

import com.fireblade.minisocialmedia.persistence.PersistenceModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CommentViewFragmentProvider {
  @ContributesAndroidInjector(modules = [CommentViewFragmentModule::class, PersistenceModule::class])
  abstract fun bindCommentViewFragment(): CommentViewFragment
}