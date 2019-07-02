package com.fireblade.detail

import com.fireblade.persistence.PersistenceModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailedPostActivityProvider {
  @ContributesAndroidInjector(modules = [DetailedPostActivityModule::class, PersistenceModule::class])
  abstract fun bindDetailsActivity(): DetailedPostActivity
}