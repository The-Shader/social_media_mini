package com.fireblade.minisocialmedia.details

import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [DetailsActivitySubcomponent::class])
abstract class DetailsActivityModule {
  @Binds
  @IntoMap
  @ClassKey(DetailsActivity::class)
  internal abstract fun bindMainActivityInjectorFactory(builder: DetailsActivitySubcomponent.Builder): AndroidInjector.Factory<*>
}
