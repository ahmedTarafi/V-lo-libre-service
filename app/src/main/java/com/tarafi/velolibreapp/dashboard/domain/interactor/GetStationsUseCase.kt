package com.tarafi.velolibreapp.dashboard.domain.interactor

import com.tarafi.velolibreapp.dashboard.domain.bean.Station

interface GetStationsUseCase {

    interface Callback{
        fun onGetStationsSuccess(stationsList: ArrayList<Station>)
        fun onGetStationsFailure()
    }

    fun execute(callback: Callback)
}