package com.fireblade.minisocialmedia.listview

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ListViewFragmentProvider  {
  @ContributesAndroidInjector(modules = [ListViewFragmentModule::class])
  abstract fun bindHomeFragment(): ListViewFragment
}