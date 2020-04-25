package com.tarafi.velolibreapp.dashboard.app.iviews

import com.tarafi.velolibreapp.dashboard.domain.bean.Station
import java.util.ArrayList

interface DashboardIView {
    fun showStationsOnMap(stationsList: ArrayList<Station>)

}