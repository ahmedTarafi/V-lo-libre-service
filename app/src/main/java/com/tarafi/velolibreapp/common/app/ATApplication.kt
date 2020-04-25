package com.tarafi.velolibreapp.common.app

import android.app.Application
import com.tarafi.velolibreapp.common.di.ApplicationComponent
import com.tarafi.velolibreapp.common.di.ApplicationModule
import com.tarafi.velolibreapp.common.di.DaggerApplicationComponent

class ATApplication : Application() {
    private lateinit var appComponent: ApplicationComponent;
    private lateinit var appModule: ApplicationModule

    override fun onCreate() {
        super.onCreate()
        initializeInjector()
    }

    private fun initializeInjector(){
        appModule= ApplicationModule(this)
        appComponent = DaggerApplicationComponent.builder().build()
    }

    fun getAppComponent(): ApplicationComponent {
        return appComponent
    }

    fun getAppModule(): ApplicationModule {
        return appModule
    }


}