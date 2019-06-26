package com.fireblade.minisocialmedia.details

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailsActivityProvider {
  @ContributesAndroidInjector(modules = [DetailsActivityModule::class])
  abstract fun bindDetailsActivity(): DetailsActivity
}