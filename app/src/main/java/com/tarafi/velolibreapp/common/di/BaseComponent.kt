package com.tarafi.velolibreapp.common.di

import com.tarafi.velolibreapp.common.app.fragment.BaseFragment
import dagger.Component

@PerFragment
@Component(dependencies = arrayOf(ApplicationComponent::class) , modules = arrayOf(BaseModule::class))
interface BaseComponent {
    fun inject(baseFragment: BaseFragment)
}