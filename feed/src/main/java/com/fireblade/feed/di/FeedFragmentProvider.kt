package com.fireblade.feed.di

import com.fireblade.detail.di.DetailedPostActivityProvider
import com.fireblade.feed.ui.FeedFragment
import com.fireblade.persistence.PersistenceModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FeedFragmentProvider {
  @ContributesAndroidInjector(modules = [PersistenceModule::class, DetailedPostActivityProvider::class])
  abstract fun bindFeedViewFragment(): FeedFragment
}