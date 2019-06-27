package com.fireblade.minisocialmedia.listview

import com.fireblade.minisocialmedia.details.DetailedPostActivityProvider
import com.fireblade.minisocialmedia.network.NetworkModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ListViewFragmentProvider {
  @ContributesAndroidInjector(modules = [ListViewFragmentModule::class, NetworkModule::class, DetailedPostActivityProvider::class])
  abstract fun bindListViewFragment(): ListViewFragment
}