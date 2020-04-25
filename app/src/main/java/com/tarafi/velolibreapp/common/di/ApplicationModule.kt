package com.tarafi.velolibreapp.common.di

import android.content.Context
import com.tarafi.velolibreapp.common.app.ATApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(val application: ATApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application
    }
}