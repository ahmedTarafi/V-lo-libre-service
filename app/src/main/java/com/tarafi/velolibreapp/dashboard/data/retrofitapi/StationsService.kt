package com.tarafi.velolibreapp.dashboard.data.retrofitapi

import com.tarafi.velolibreapp.dashboard.data.entities.StationJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val STATIONS_LIST_URL = "stations"

interface StationsService {
    @GET(STATIONS_LIST_URL)
    fun getAllStations(@Query("apiKey") apikey: String): Call<List<StationJson>>

}