package com.tarafi.velolibreapp.dashboard.di

import com.tarafi.velolibreapp.common.di.ApplicationComponent
import com.tarafi.velolibreapp.common.di.PerFragment
import com.tarafi.velolibreapp.dashboard.app.fragment.DashboardFragment
import dagger.Component


@PerFragment
@Component(dependencies = arrayOf(ApplicationComponent::class) , modules = arrayOf(DashboardModule::class))
interface DashboardComponent {
    fun inject(dashboardFragment: DashboardFragment)
}