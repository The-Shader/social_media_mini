package com.fireblade.minisocialmedia.details

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface DetailsActivitySubcomponent: AndroidInjector<DetailsActivity> {

  @Subcomponent.Builder
  abstract class Builder: AndroidInjector.Builder<DetailsActivity>()
}