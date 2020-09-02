package com.fireblade.detail.di

import com.fireblade.detail.ui.DetailedPostActivity
import com.fireblade.persistence.PersistenceModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailedPostActivityProvider {
  @ContributesAndroidInjector(modules = [PersistenceModule::class])
  abstract fun bindDetailsActivity(): DetailedPostActivity
}