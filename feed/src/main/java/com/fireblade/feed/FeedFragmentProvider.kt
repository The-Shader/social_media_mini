package com.fireblade.feed

import com.fireblade.detail.DetailedPostActivityProvider
import com.fireblade.persistence.PersistenceModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FeedFragmentProvider {
  @ContributesAndroidInjector(modules = [FeedFragmentModule::class, PersistenceModule::class, DetailedPostActivityProvider::class])
  abstract fun bindListViewFragment(): FeedFragment
}