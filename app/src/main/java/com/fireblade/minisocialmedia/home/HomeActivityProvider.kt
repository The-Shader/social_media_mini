package com.fireblade.minisocialmedia.home

import com.fireblade.feed.di.FeedFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface HomeActivityProvider {
  @ContributesAndroidInjector(modules = [HomeActivityModule::class, FeedFragmentProvider::class])
  fun bindHomeActivity(): HomeActivity
}