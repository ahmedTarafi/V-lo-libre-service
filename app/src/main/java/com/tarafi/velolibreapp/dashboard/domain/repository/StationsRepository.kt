package com.tarafi.velolibreapp.dashboard.domain.repository

import com.tarafi.velolibreapp.dashboard.domain.bean.Station

interface StationsRepository {
    interface GetAllStationsCallback {
        fun onGetAllStationsSuccess(stationsList: ArrayList<Station>)
        fun onGetAllStationsFailure()
    }

    fun getAllStations(getAllStationsCallback: GetAllStationsCallback)

}