package com.tarafi.velolibreapp.common.di

import com.tarafi.velolibreapp.common.app.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
}