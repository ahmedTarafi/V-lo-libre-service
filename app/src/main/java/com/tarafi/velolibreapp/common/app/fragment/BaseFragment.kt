package com.tarafi.velolibreapp.common.app.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.tarafi.velolibreapp.common.app.ATApplication
import com.tarafi.velolibreapp.common.app.activity.MainActivity
import com.tarafi.velolibreapp.common.di.ApplicationComponent
import com.tarafi.velolibreapp.common.di.BaseModule
import com.tarafi.velolibreapp.common.di.DaggerBaseComponent
import javax.inject.Inject

open class BaseFragment : Fragment(){
    @Inject
    lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeInjector(getApplicationComponent())
    }

     open fun initializeInjector(applicationComponent: ApplicationComponent){
        val baseComponent = DaggerBaseComponent.builder().applicationComponent(applicationComponent).baseModule(
            BaseModule(activity as MainActivity)
        ).build()
        baseComponent.inject(this)
    }

    private fun getApplicationComponent() : ApplicationComponent {
        val mvpDemoApplication = activity!!.application as ATApplication
        return mvpDemoApplication.getAppComponent()
    }
}