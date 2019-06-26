package com.fireblade.minisocialmedia.home

import com.fireblade.minisocialmedia.listview.ListViewFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface HomeActivityProvider {
  @ContributesAndroidInjector(modules = [HomeActivityModule::class, ListViewFragmentProvider::class])
  fun bindHomeActivity(): HomeActivity
}