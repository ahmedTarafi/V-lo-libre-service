package com.tarafi.velolibreapp.dashboard.domain.interactor

import com.tarafi.velolibreapp.dashboard.domain.bean.Station
import com.tarafi.velolibreapp.dashboard.domain.repository.StationsRepository
import javax.inject.Inject

class GetStationsUseCaseImpl @Inject constructor(val stationsRepository: StationsRepository) :GetStationsUseCase{
    override fun execute(callback: GetStationsUseCase.Callback) {
        stationsRepository.getAllStations(object:StationsRepository.GetAllStationsCallback{
            override fun onGetAllStationsSuccess(stationsList: ArrayList<Station>) {
                callback.onGetStationsSuccess(stationsList)
            }

            override fun onGetAllStationsFailure() {
                callback.onGetStationsFailure()
            }

        })
    }
}