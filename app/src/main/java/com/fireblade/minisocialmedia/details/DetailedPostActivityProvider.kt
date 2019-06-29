package com.fireblade.minisocialmedia.details

import com.fireblade.minisocialmedia.persistence.PersistenceModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailedPostActivityProvider {
  @ContributesAndroidInjector(modules = [DetailedPostActivityModule::class, PersistenceModule::class])
  abstract fun bindDetailsActivity(): DetailedPostActivity
}