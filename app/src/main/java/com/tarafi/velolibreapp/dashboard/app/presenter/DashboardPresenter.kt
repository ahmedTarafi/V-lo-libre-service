package com.tarafi.velolibreapp.dashboard.app.presenter

import com.tarafi.velolibreapp.dashboard.app.iviews.DashboardIView
import com.tarafi.velolibreapp.dashboard.domain.bean.Station
import com.tarafi.velolibreapp.dashboard.domain.interactor.GetStationsUseCase
import javax.inject.Inject

class DashboardPresenter @Inject constructor(val getStationsUseCase: GetStationsUseCase) {
    private var dashboardIView:DashboardIView? = null

    fun setView(dashboardIView: DashboardIView) {
        this.dashboardIView = dashboardIView
    }

    fun fetchStations() {
        getStationsUseCase.execute(object : GetStationsUseCase.Callback {
            override fun onGetStationsSuccess(stationsList: ArrayList<Station>) {
                dashboardIView?.showStationsOnMap(stationsList)
            }

            override fun onGetStationsFailure() {

            }

        })
    }

}