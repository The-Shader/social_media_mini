package com.fireblade.minisocialmedia.details

import com.fireblade.minisocialmedia.comments.CommentViewFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailedPostActivityProvider {
  @ContributesAndroidInjector(modules = [DetailedPostActivityModule::class, CommentViewFragmentProvider::class])
  abstract fun bindDetailsActivity(): DetailedPostActivity
}