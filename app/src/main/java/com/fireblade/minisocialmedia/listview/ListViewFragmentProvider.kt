package com.fireblade.minisocialmedia.listview

import com.fireblade.minisocialmedia.network.NetworkModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ListViewFragmentProvider {
  @ContributesAndroidInjector(modules = [ListViewFragmentModule::class, NetworkModule::class])
  abstract fun bindListViewFragment(): ListViewFragment
}