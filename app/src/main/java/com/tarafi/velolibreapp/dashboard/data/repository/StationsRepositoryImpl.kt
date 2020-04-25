package com.tarafi.velolibreapp.dashboard.data.repository

import com.tarafi.velolibreapp.dashboard.data.entities.StationJson
import com.tarafi.velolibreapp.dashboard.data.entities.StationMapper
import com.tarafi.velolibreapp.dashboard.data.retrofitapi.StationsService
import com.tarafi.velolibreapp.dashboard.domain.repository.StationsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class StationsRepositoryImpl @Inject constructor(
    val stationsService: StationsService,
    val stationMapper: StationMapper
) : StationsRepository {
    override fun getAllStations(getAllStationsCallback: StationsRepository.GetAllStationsCallback) {
        stationsService.getAllStations("930a8cc2e660b4efe9d66f1e2787ec106827359a")
            .enqueue(object : Callback<List<StationJson>> {
                override fun onFailure(call: Call<List<StationJson>>, t: Throwable) {
                    getAllStationsCallback.onGetAllStationsFailure()
                }

                override fun onResponse(
                    call: Call<List<StationJson>>,
                    response: Response<List<StationJson>>
                ) {
                    if (response.isSuccessful) {
                        getAllStationsCallback.onGetAllStationsSuccess(stationMapper.toStationsBo(response.body()))
                    }
                }

            })
    }

}