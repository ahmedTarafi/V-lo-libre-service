package com.tarafi.velolibreapp.common.di

import com.tarafi.velolibreapp.common.app.activity.MainActivity
import dagger.Module
import dagger.Provides

@Module
class BaseModule(val mainActivity: MainActivity) {

    @Provides
    @PerFragment
    fun provideActivity(): MainActivity {
        return mainActivity
    }
}