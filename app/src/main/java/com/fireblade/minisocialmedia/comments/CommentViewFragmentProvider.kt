package com.fireblade.minisocialmedia.comments

import com.fireblade.minisocialmedia.network.NetworkModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CommentViewFragmentProvider {
  @ContributesAndroidInjector(modules = [CommentViewFragmentModule::class, NetworkModule::class])
  abstract fun bindCommentViewFragment(): CommentViewFragment
}