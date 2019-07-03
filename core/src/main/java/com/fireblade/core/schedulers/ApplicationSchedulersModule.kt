package com.fireblade.core.schedulers

import dagger.Module
import dagger.Provides

@Module
class ApplicationSchedulersModule {

    @Provides
    fun provideApplicationSchedulers(): ISchedulers = ApplicationSchedulers()
}