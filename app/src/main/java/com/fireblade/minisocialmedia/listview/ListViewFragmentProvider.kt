package com.fireblade.minisocialmedia.listview

import com.fireblade.minisocialmedia.persistence.PersistenceModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ListViewFragmentProvider {
  @ContributesAndroidInjector(modules = [ListViewFragmentModule::class, PersistenceModule::class])
  abstract fun bindListViewFragment(): ListViewFragment
}